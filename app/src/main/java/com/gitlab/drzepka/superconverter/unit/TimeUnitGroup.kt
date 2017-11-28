package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class TimeUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_time_second, UnitSystem.DAY_OR_LESS, "s", null, this))
        addUnit(Unit(R.string.unit_group_time_minute, UnitSystem.DAY_OR_LESS, "min", "60 s", this))
        addUnit(Unit(R.string.unit_group_time_hour, UnitSystem.DAY_OR_LESS, "h", "60 min", this))
        addUnit(Unit(R.string.unit_group_time_millisecond, UnitSystem.DAY_OR_LESS, "ms", "0.001 s", this))
        addUnit(Unit(R.string.unit_group_time_day, UnitSystem.DAY_OR_LESS, "day", "24 h", this))
        addUnit(Unit(R.string.unit_group_time_week, UnitSystem.MORE_THAN_DAY, "week", "7 day", this))
        addUnit(Unit(R.string.unit_group_time_month, UnitSystem.MORE_THAN_DAY, "mon", "30 day", this))
        addUnit(Unit(R.string.unit_group_time_year, UnitSystem.MORE_THAN_DAY, "year", "365 day", this))
    }
}