package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitCategory

class LengthUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_length_meter, UnitCategory.METRIC, "m", null, this))
        addUnit(Unit(R.string.unit_group_length_kilometer, UnitCategory.METRIC, "km", "1000 m", this))
        addUnit(Unit(R.string.unit_group_length_mile, UnitCategory.ENGLISH, "mi", "1,609344 km", this))
        addUnit(Unit(R.string.unit_group_length_inch, UnitCategory.ENGLISH, "in", "0,0254 m", this))
    }
}