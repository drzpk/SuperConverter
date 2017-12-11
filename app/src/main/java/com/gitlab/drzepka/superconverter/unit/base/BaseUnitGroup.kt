package com.gitlab.drzepka.superconverter.unit.base

/**
 * Baza dla grup jednostek. Pierwsza jednostka w tablicy [units] jest punktem odniesienia,
 */
abstract class BaseUnitGroup {

    /** Jednostki dostępne w tej grupie. */
    val units = ArrayList<BaseUnit>()

    /**
     * Dodaje nową jednostkę do grupy.
     */
    protected fun addUnit(baseUnit: BaseUnit) {
        if (baseUnit is Unit) {
            if (units.isNotEmpty() && baseUnit.converter == null && !baseUnit.hasCustomConverters())
                throw IllegalArgumentException("Tylko jednostka będąca punktem odniesienia może nie posiadać przelicznika")
        }

        units.add(baseUnit)
    }
}