package com.gitlab.drzepka.superconverter.unit.base

import android.support.annotation.StringRes

/**
 * Klasa bazowa dla wszystkich jednostek
 *
 * @param unitName nazwa jednostki
 * @param system opcjonalna kategoria jednostki
 * @param symbol krótka nazwa, symbol jednostki
 */
abstract class BaseUnit(
        @StringRes val unitName: Int,
        val system: UnitSystem,
        val symbol: String) {

    /**
     *  Konwetuje tą jednostkę na inną.
     */
    abstract fun convert(to: BaseUnit, value: Double): Double
}