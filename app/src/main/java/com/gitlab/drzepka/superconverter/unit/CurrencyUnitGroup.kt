package com.gitlab.drzepka.superconverter.unit

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnit
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem
import org.w3c.dom.Document
import org.w3c.dom.Element
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

class CurrencyUnitGroup : BaseUnitGroup() {

    private lateinit var context: Context
    private lateinit var preferences: SharedPreferences

    fun initialize(context: Context) {
        this.context = context

        // Dodanie jednostek
        // Ostatnia aktualizacja przeliczników: 11.12.2017
        addUnit(CurrencyUnit(R.string.unit_group_currency_pln, "PLN", 1.000000))
        addUnit(CurrencyUnit(R.string.unit_group_currency_usd, "USD", 3.563300))
        addUnit(CurrencyUnit(R.string.unit_group_currency_aud, "AUD", 2.684000))
        addUnit(CurrencyUnit(R.string.unit_group_currency_cad, "CAD", 2.774300))
        addUnit(CurrencyUnit(R.string.unit_group_currency_eur, "EUR", 4.203800))
        addUnit(CurrencyUnit(R.string.unit_group_currency_huf, "HUF", 0.013374))
        addUnit(CurrencyUnit(R.string.unit_group_currency_uah, "UAH", 0.131200))
        addUnit(CurrencyUnit(R.string.unit_group_currency_jpy, "JPY", 0.031431))
        addUnit(CurrencyUnit(R.string.unit_group_currency_mxn, "MXN", 0.187900))
        addUnit(CurrencyUnit(R.string.unit_group_currency_rub, "RUB", 0.060200))
        addUnit(CurrencyUnit(R.string.unit_group_currency_cny, "CNY", 0.538400))
        addUnit(CurrencyUnit(R.string.unit_group_currency_inr, "INR", 0.055375))
        addUnit(CurrencyUnit(R.string.unit_group_currency_sek, "SEK", 0.419400))
        addUnit(CurrencyUnit(R.string.unit_group_currency_nok, "NOK", 0.425000))

        // Załadowanie przeliczników
        preferences = context.getSharedPreferences("currencies", Context.MODE_PRIVATE)
        units.forEach {
            val storedRatio = preferences.getFloat(it.symbol, 0f)
            if (storedRatio > 0)
                (it as CurrencyUnit).ratio = storedRatio.toDouble()
        }

        // Sprawdzenie, czy przeliczniki wymagają aktualizacji
        val lastUpdate = getCalendar(preferences.getLong("last_update", 0L))
        val today = getCalendar(System.currentTimeMillis())
        if (today.after(lastUpdate)) {
            // Aktualizacja przeliczników
            CurrencyUpdater().execute()
        }
    }

    /**
     * Zwraca datę zawierającą tylko dzień, bez czasu.
     */
    private fun getCalendar(timestamp: Long): Calendar {
        val old = Calendar.getInstance()
        old.timeInMillis = timestamp

        val new = Calendar.getInstance()
        new.set(old.get(Calendar.YEAR), old.get(Calendar.MONTH), old.get(Calendar.DAY_OF_MONTH))

        return new
    }


    /**
     * Zapisuje obecne przeliczniki do pliku konfiguracyjnego
     */
    private fun dumpUnits() {
        val editor = preferences.edit()
        val today = getCalendar(System.currentTimeMillis())
        editor.putLong("last_update", today.timeInMillis)

        units.forEach { editor.putFloat(it.symbol, (it as CurrencyUnit).ratio.toFloat()) }
        editor.apply()
    }

    private class CurrencyUnit(unitName: Int, symbol: String, var ratio: Double) : BaseUnit(unitName, UnitSystem.DEFAULT, symbol) {

        override fun convert(to: BaseUnit, value: Double): Double
                = Math.floor(value * ratio / (to as CurrencyUnit).ratio * 100000) / 100000
    }

    @SuppressLint("StaticFieldLeak")
    private inner class CurrencyUpdater : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg params: Unit?) {
            val url = URL("http://api.nbp.pl/api/exchangerates/tables/a/today/")
            val connection = url.openConnection() as HttpURLConnection
            connection.addRequestProperty("Content-type", "application/xml")

            try {
                val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                val document = builder.parse(connection.inputStream)
                Handler(Looper.getMainLooper()).run { parse(document) }
            } catch (exception: Exception) {
                exception.printStackTrace()
            } finally {
                connection.disconnect()
            }
        }

        private fun parse(document: Document) {
            fun setRatio(symbol: String, value: Double) {
                val unit = units.firstOrNull { it.symbol == symbol } as? CurrencyUnit
                unit?.ratio = value
            }

            val elements = document.getElementsByTagName("Rate")
            for (i in 0 until elements.length) {
                val element = elements.item(i) as Element
                val symbol = (element.getElementsByTagName("Code").item(0) as Element).textContent
                val ratio = (element.getElementsByTagName("Mid").item(0) as Element).textContent.toDouble()
                setRatio(symbol, ratio)
            }

            // Zapisanie aktualnych przeliczników
            dumpUnits()
        }
    }
}