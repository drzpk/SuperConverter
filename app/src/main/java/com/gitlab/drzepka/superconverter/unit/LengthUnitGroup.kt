package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class LengthUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_length_meter, UnitSystem.METRIC, "m", null, this))
        addUnit(Unit(R.string.unit_group_length_kilometer, UnitSystem.METRIC, "km", "1000 m", this))
        addUnit(Unit(R.string.unit_group_length_mile, UnitSystem.ENGLISH, "mi", "1,609344 km", this))
        addUnit(Unit(R.string.unit_group_length_inch, UnitSystem.ENGLISH, "in", "0,0254 m", this))
    }
}