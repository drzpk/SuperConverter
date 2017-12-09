package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class TemperatureUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_temperature_celsius,
                UnitSystem.DEFAULT,
                "oC",
                null,
                this))

        addUnit(Unit(R.string.unit_group_temperature_fahrenheit,
                UnitSystem.DEFAULT,
                "oF",
                null,
                this,
                { oc -> oc * 1.8 + 32 },
                { of -> (of - 32) / 1.8 }))

        addUnit(Unit(R.string.unit_group_temperature_kelvin,
                UnitSystem.DEFAULT,
                "K",
                null,
                this,
                { oc -> oc + 273.15 },
                { k -> k - 273.15 }))
    }
}