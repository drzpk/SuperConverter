package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitCategory

class MassUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_mass_kilogram, UnitCategory.METRIC, "kg", null, this))
        addUnit(Unit(R.string.unit_group_mass_gram, UnitCategory.METRIC, "g", "0.001 kg", this))
        addUnit(Unit(R.string.unit_group_mass_ton, UnitCategory.METRIC, "t", "1000 kg", this))
        addUnit(Unit(R.string.unit_group_mass_pound, UnitCategory.ENGLISH, "lb", "0,45359237 kg", this))
        addUnit(Unit(R.string.unit_group_mass_ounce, UnitCategory.ENGLISH, "oz", "0,0625 lb", this))
    }
}