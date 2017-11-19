package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class MassUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_mass_kilogram, UnitSystem.METRIC, "kg", null, this))
        addUnit(Unit(R.string.unit_group_mass_gram, UnitSystem.METRIC, "g", "0.001 kg", this))
        addUnit(Unit(R.string.unit_group_mass_ton, UnitSystem.METRIC, "t", "1000 kg", this))
        addUnit(Unit(R.string.unit_group_mass_pound, UnitSystem.ENGLISH, "lb", "0,45359237 kg", this))
        addUnit(Unit(R.string.unit_group_mass_ounce, UnitSystem.ENGLISH, "oz", "0,0625 lb", this))
    }
}