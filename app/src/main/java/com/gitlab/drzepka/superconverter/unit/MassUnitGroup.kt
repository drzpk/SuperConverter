package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit

class MassUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_mass_kilogram, null, "kg", null, this))
        addUnit(Unit(R.string.unit_group_mass_gram, null, "g", "0.001 kg", this))
        addUnit(Unit(R.string.unit_group_mass_ton, null, "t", "1000 kg", this))
        addUnit(Unit(R.string.unit_group_mass_pound, null, "lb", "0,45359237 kg", this))
        addUnit(Unit(R.string.unit_group_mass_ounce, null, "oz", "0,0625 lb", this))
    }
}