package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class VolumeUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_volume_liter, UnitSystem.METRIC, "l", null, this))
        addUnit(Unit(R.string.unit_group_volume_mliter, UnitSystem.METRIC, "ml", "0.001 l", this))
        addUnit(Unit(R.string.unit_group_volume_cubmeter, UnitSystem.METRIC, "m3", "1000 l", this))
        addUnit(Unit(R.string.unit_group_volume_ounce, UnitSystem.IMPERIAL, "floz", "28.4131 ml", this))
        addUnit(Unit(R.string.unit_group_volume_pint, UnitSystem.IMPERIAL, "pt", "20 floz", this))
        addUnit(Unit(R.string.unit_group_volume_quart, UnitSystem.IMPERIAL, "qt", "2 pt", this))
        addUnit(Unit(R.string.unit_group_volume_gallon, UnitSystem.IMPERIAL, "gal", "160 floz", this))
        addUnit(Unit(R.string.unit_group_volume_bushel, UnitSystem.IMPERIAL, "bu", "8 gal", this))
    }
}