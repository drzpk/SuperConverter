package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit

class LengthUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_length_meter, null, "m", null, this))
        addUnit(Unit(R.string.unit_group_length_kilometer, null, "km", "1000 m", this))
        addUnit(Unit(R.string.unit_group_length_mile, null, "mi", "1,609344 km", this))
        addUnit(Unit(R.string.unit_group_length_inch, null, "in", "0,0254 m", this))
    }
}