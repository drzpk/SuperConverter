package com.gitlab.drzepka.superconverter

import android.animation.Animator
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gitlab.drzepka.superconverter.unit.CurrencyUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.UnitType
import com.gitlab.drzepka.superconverter.view.ChooseUnitDialog
import com.gitlab.drzepka.superconverter.view.UnitConversionLayout
import com.gitlab.drzepka.superconverter.view.UnitHolderLayout

class ConverterActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var upperHolder: UnitHolderLayout
    private lateinit var lowerHolder: UnitHolderLayout
    private lateinit var leftUnit: UnitConversionLayout
    private lateinit var rightUnit: UnitConversionLayout
    private lateinit var unitSwapper: View

    private lateinit var drawerAdapter: DrawerAdapter
    private lateinit var activeType: UnitType
    private var animatingSwap = false
    private var startTypeIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        drawerLayout = findViewById(R.id.converter_drawer)
        val list = findViewById<ListView>(R.id.converter_drawer_list)
        drawerAdapter = DrawerAdapter()
        list.adapter = drawerAdapter
        list.onItemClickListener = this
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, android.R.string.unknownName, android.R.string.unknownName)
        drawerLayout.addDrawerListener(drawerToggle)

        leftUnit = findViewById(R.id.converter_unit_conversion_left)
        rightUnit = findViewById(R.id.converter_unit_conversion_right)
        rightUnit.disabled = true
        leftUnit.value = 0.0
        leftUnit.onChangeListener = { updateConverson() }

        upperHolder = findViewById(R.id.converter_unit_holder_1)
        lowerHolder = findViewById(R.id.converter_unit_holder_2)
        upperHolder.setOnClickListener { onHolderClick(true) }
        lowerHolder.setOnClickListener { onHolderClick(false) }

        unitSwapper = findViewById(R.id.converter_unit_holder_swapper)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Inicjalizacja przelicznika walut
        val group = UnitType.values().firstOrNull { it.unitGroup is CurrencyUnitGroup }?.unitGroup
        (group as? CurrencyUnitGroup)?.initialize(this)

        // Pobranie indeksu aktywnej grupy z konfiguracji
        val prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        startTypeIndex = Math.min(prefs.getInt("unit_group", 0), UnitType.values().size)
        if (UnitType.values().size >= startTypeIndex)
            startTypeIndex = 0
        setActiveType(UnitType.values()[startTypeIndex])
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onStop() {
        super.onStop()

        // Zapisanie indeksu, jeśli został zmieniony
        val newIndex = UnitType.values().indexOfFirst { it == activeType }
        if (startTypeIndex != newIndex) {
            val prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE)
            prefs.edit().putInt("unit_group", newIndex).apply()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (drawerToggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }

    private fun setActiveType(unitType: UnitType) {
        activeType = unitType

        // wybranie dwóch pierwszych jednostek z grupy jako aktywnych
        upperHolder.currentType = unitType
        lowerHolder.currentType = unitType
        upperHolder.unit = unitType.unitGroup.units[0]
        lowerHolder.unit = unitType.unitGroup.units[1]
        leftUnit.unit = unitType.unitGroup.units[0]
        rightUnit.unit = unitType.unitGroup.units[1]
        updateConverson()
    }

    /**
     * Wywoływana, gdy przycisk (strzałki) do zmiany kolejności jednostek zostanie wciśnięty.
     */
    @Suppress("UNUSED_PARAMETER")
    fun swapUnits(view: View?) {
        if (animatingSwap)
            return

        // zamiana holderów
        val tmp = upperHolder.unit
        upperHolder.unit = lowerHolder.unit
        lowerHolder.unit = tmp

        // zamiana jednostek
        val tmpUnit = leftUnit.unit
        leftUnit.unit = rightUnit.unit
        rightUnit.unit = tmpUnit
        val tmpValue = leftUnit.value
        leftUnit.value = rightUnit.value
        rightUnit.value = tmpValue

        // animacja przysicku do zmiany jednostek
        unitSwapper.animate()
                .rotationBy(180f)
                .setDuration(350L)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator?) {
                        animatingSwap = true
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        animatingSwap = false
                    }

                    override fun onAnimationRepeat(animation: Animator?) = Unit
                    override fun onAnimationCancel(animation: Animator?) = Unit
                })
    }

    /**
     * Aktualizuje konwersję leftUnit -> rightUnit.
     */
    private fun updateConverson() {
        val value = leftUnit.unit.convert(rightUnit.unit, leftUnit.value)
        rightUnit.value = value
    }

    /**
     * Wywoływana w momencie kliknięcia jednego z widoków z nazwą jednostki.
     */
    private fun onHolderClick(upper: Boolean) {
        val excluded = if (upper) upperHolder.unit else lowerHolder.unit
        val targetHolder = if (upper) upperHolder else lowerHolder
        val targetUnitLayout = if (upper) leftUnit else rightUnit
        val otherUnit = (if (upper) lowerHolder else upperHolder).unit

        ChooseUnitDialog().show(this, activeType, excluded, { chosen ->
            if (chosen != otherUnit) {
                // normalna zmiana jednostek
                targetHolder.unit = chosen
                targetUnitLayout.unit = chosen
                updateConverson()
            }
            else {
                // wybrana jednostka znajduje się w drugim holderze, wystarczy zamienić jednostki
                swapUnits(null)
            }
        })
    }

    /**
     * Wywoływana, gdy kategoria jednostek zostanie wybrana.
     */
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        setActiveType(UnitType.values()[position])
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    inner class DrawerAdapter : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: layoutInflater.inflate(R.layout.drawer_list_item, parent, false)
            val item = getItem(position)

            view.findViewById<ImageView>(R.id.drawer_list_item_image).setImageResource(item.unitIcon)
            view.findViewById<TextView>(R.id.drawer_list_item_text).setText(item.unitName)
            return view
        }

        override fun getItem(position: Int): UnitType = UnitType.values()[position]
        override fun getItemId(position: Int): Long = 0L
        override fun getCount(): Int = UnitType.values().size
    }
}