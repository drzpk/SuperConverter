package com.gitlab.drzepka.superconverter

import com.gitlab.drzepka.superconverter.unit.base.BaseUnit
import com.gitlab.drzepka.superconverter.unit.base.UnitType
import org.junit.Assert
import org.junit.Test
import java.util.*

class UnitConversionTest {

    companion object {
        /** Maksymalny błąd bezwsględny. */
        val ERROR_MARGIN = 0.1
        /** Maksymalne rozwinięcie dziesiętne porównywanych wartości. */
        val MAX_DECIMAL_LENGTH = 5
    }

    @Test
    fun lengthConversion() {
        val type = UnitType.LENGTH
        check("km", "m", 1000.0, type)
        check("m", "in", 39.3700787, type)
        check("mi", "in", 63360.0, type)
    }

    @Test
    fun massConversion() {
        val type = UnitType.MASS
        check("t", "kg", 1000.0, type)
        check("oz", "g", 28.3495231, type)
        check("lb", "kg", 0.45359237, type)
        check("lb", "oz", 16.0, type)
    }

    /**
     * Porównuje wartości zwrócone przez konwerter jednostek z wartościami prawidłowymi.
     * @param firstUnitName symbol pierwszej jednostki
     * @param secondUnitName symbol drugiej jednostki
     * @param ratio stosunek pierwszej jednostki do drugiej
     * @param unitType typ jednostek do przetestowania
     */
    private fun check(firstUnitName: String, secondUnitName: String, ratio: Double, unitType: UnitType) {
        val firstUnit = getUnitBySymbol(firstUnitName, unitType)
        val secondUnit = getUnitBySymbol(secondUnitName, unitType)

        println("Testowanie małych wartości")
        val smallValue = Random().nextDouble() - 0.5
        checkValue(firstUnit, secondUnit, smallValue, smallValue * ratio)

        println("Testowanie normalnych wartości")
        val normalValue = Random().nextDouble() * 25
        checkValue(firstUnit, secondUnit, normalValue, normalValue * ratio)

        println("Testownaie dużych wartości")
        val hugeValue = Random().nextDouble() * 1000
        checkValue(firstUnit, secondUnit, hugeValue, hugeValue * ratio)
    }

    /**
     * Sprawdza, czy dana wartość mieści się marginesie błędu (błąd bezwzględny).
     */
    private fun checkValue(firstUnit: BaseUnit, secondUnit: BaseUnit, value: Double, expected: Double) {
        // Wyniki są porównywane tylko do określonego rozwinięcia dziesiętnego
        val divisor = Math.pow(10.0, MAX_DECIMAL_LENGTH.toDouble())
        val converted = Math.round(firstUnit.convert(secondUnit, value) * divisor) / divisor
        val expectedRounded = Math.round(expected * divisor) / divisor

        // Spraawdzenie wyniku
        val error = Math.abs((expectedRounded - converted) / expectedRounded)
        val printableError = (Math.round(error * 1000) / 10).toString() + '%'
        println("Konwersja [${firstUnit.symbol}] -> [${secondUnit.symbol}] różni się od wzorcowej o $printableError " +
                "($value${firstUnit.symbol} = $converted${secondUnit.symbol})")
        Assert.assertTrue("Błąd bewzględny przekacza dozwolony próg o ${Math.abs(error - ERROR_MARGIN) * 100}%",
                Math.abs(error) <= ERROR_MARGIN)

    }

    private fun getUnitBySymbol(symbol: String, unitType: UnitType): BaseUnit {
        val unit = unitType.unitGroup.units.firstOrNull { it.symbol == symbol }
        Assert.assertNotNull("Nie znaleziono symbolu $symbol w grupie ${unitType.name}")

        return unit!!
    }
}