package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class AreaUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_area_sqmeter, UnitSystem.METRIC, "m2", null, this))
        addUnit(Unit(R.string.unit_group_area_sqcentimeter, UnitSystem.METRIC, "cm2", "0.0001 m2", this))
        addUnit(Unit(R.string.unit_group_area_ar, UnitSystem.METRIC, "ar", "100 m2", this))
        addUnit(Unit(R.string.unit_group_area_ha, UnitSystem.METRIC, "ha", "100 ar", this))
    }
}