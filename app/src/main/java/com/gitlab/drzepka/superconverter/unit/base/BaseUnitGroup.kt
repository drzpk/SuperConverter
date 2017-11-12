package com.gitlab.drzepka.superconverter.unit.base

/**
 * Baza dla grup jednostek. Pierwsza jednostka w tablicy [units] jest punktem odniesienia,
 */
abstract class BaseUnitGroup {

    /** Jednostki dostępne w tej grupie. */
    val units = ArrayList<Unit>()

    /**
     * Dodaje nową jednostkę do grupy.
     */
    protected fun addUnit(unit: Unit) {
        if (units.isNotEmpty() && unit.converter == null)
            throw IllegalArgumentException("Tylko jednostka będąca punktem odniesienia może nie posiadać przelicznika")

        units.add(unit)
    }
}