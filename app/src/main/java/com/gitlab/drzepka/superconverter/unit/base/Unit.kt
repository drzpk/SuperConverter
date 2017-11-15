package com.gitlab.drzepka.superconverter.unit.base

import android.support.annotation.StringRes

/**
 * Reprezentacja jednostki. Najważniejszym parametrem jednostki jest jej przelicnzik - parametr [converter].
 * Opisuje on, jaki jest stosunek tej jednoski do innej. Forma przelicznika jest następjąca:
 *
 * `<wartość w innej jednostce><spacja><symbol innej jednostki>`
 *
 * Jeśli jednostka jest zależna w niestandardowy sposób od innej jednostki, należy zdefiniować **obie** dedykowane
 * funkcje konwertujące. W takim wypadku parametr [converter] jest ignorowany.
 *
 * @param unitName nazwa jednostki
 * @param category opcjonalna kategoria jednostki
 * @param symbol krótka nazwa, symbol jednostki
 * @param converter przelicznik jednostki (może być null, jeśli inna jednostka posiada tą jako przelicznik)
 * @param unitGroup grupa, do której należy jednostka
 * @param customConverterFrom dedykowana funkcja konwertująca **punkt odniesienia** na tą jednostkę
 * @param customConverterTo dedykowana funkcja konwertująca tą jednostkę na **punkt odniesienia**
 */
class Unit(
        @StringRes val unitName: Int,
        val category: UnitCategory,
        val symbol: String,
        val converter: String?,
        private val unitGroup: BaseUnitGroup,
        private val customConverterFrom: ((Double) -> Double)? = null,
        private val customConverterTo: ((Double) -> Double)? = null) {

    /** Przelicznik względem głównej jednostki */
    private val masterRatio = initialize()

    init {
        if ((customConverterFrom == null) xor (customConverterTo == null))
            throw IllegalArgumentException("tylko dwie lub żadna dedykowana funkcja konwertująca może być zdefiniowana")
    }

    private fun initialize(): Double {
        if (customConverterFrom != null) {
            // Jednostka posiada dedykowaną funkcję-przelicznik
            return 0.0
        }

        if (converter == null)
            return 1.0

        // Brak specjalnej funkcji przeliczającej
        if (!converter.contains(' '))
            throw IllegalStateException("brak separatora w przeliczniku jednostki")

        val parts = converter.split(' ')
        val value = parts[0].replace(',', '.').toDouble()
        val remoteUnit = unitGroup.units.firstOrNull { it.symbol == parts.last() } ?:
                throw IllegalStateException("nie znaleziono jednoski wskazanej w przeliczniku" +
                        "(obiekt: $unitName, jednostka przelicznika: ${parts.last()})")

        return value * remoteUnit.masterRatio
    }

    /**
     *  Konwetuje tą jednostkę na inną.
     */
    fun convert(to: Unit, value: Double): Double {
        val half = customConverterFrom?.invoke(value) ?: value * masterRatio
        return to.customConverterTo?.invoke(half) ?: half / to.masterRatio
    }
}
