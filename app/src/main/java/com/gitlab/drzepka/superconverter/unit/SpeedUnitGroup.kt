package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class SpeedUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_speed_ms, UnitSystem.METRIC, "m/s", null, this))
        addUnit(Unit(R.string.unit_group_speed_kmh, UnitSystem.METRIC, "km/h", "0.277777778 m/s", this))
        addUnit(Unit(R.string.unit_group_speed_mach, UnitSystem.METRIC, "ma", "1225 km/h", this))
        addUnit(Unit(R.string.unit_group_speed_mph, UnitSystem.ENGLISH, "mph", "1.6093 km/h", this))
        addUnit(Unit(R.string.unit_group_speed_mps, UnitSystem.ENGLISH, "mps", "3600 mph", this))
        addUnit(Unit(R.string.unit_group_speed_knot, UnitSystem.ENGLISH, "kn", "1.852 km/h", this))
    }
}