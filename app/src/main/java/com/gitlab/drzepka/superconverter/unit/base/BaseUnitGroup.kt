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

    /**
     * Zwraca jednostki pogrupowane według systemu, do którego należą. Jednostki nie należące do żadnego systemu
     * będą miały klucz ustawiony na `null`.
     */
    fun groupBySystem(): Map<UnitSystem?, ArrayList<Unit>> {
        val map = HashMap<UnitSystem?, ArrayList<Unit>>()
        units.forEach {
            if (!map.containsKey(it.system))
                map[it.system] = ArrayList()
            map[it.system]?.add(it)
        }

        return map
    }
}