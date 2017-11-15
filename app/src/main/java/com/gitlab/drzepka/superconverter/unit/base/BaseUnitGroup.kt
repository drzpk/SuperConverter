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
     * Zwraca jednostki pogrupowane według kategorii, do których należą. Jednostki nie należące do żadnej grupy
     * będą miały klucz ustawiony na `null`.
     */
    fun groupByCategory(): Map<UnitCategory?, ArrayList<Unit>> {
        val map = HashMap<UnitCategory?, ArrayList<Unit>>()
        units.forEach {
            if (!map.containsKey(it.category))
                map[it.category] = ArrayList()
            map[it.category]?.add(it)
        }

        return map
    }
}