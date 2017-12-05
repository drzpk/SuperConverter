package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class EnergyUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_energy_joul, UnitSystem.METRIC, "J", null, this))
        addUnit(Unit(R.string.unit_group_energy_kjoul, UnitSystem.METRIC, "kJ", "1000 J", this))
        addUnit(Unit(R.string.unit_group_energy_wh, UnitSystem.METRIC, "Wh", "3600 J", this))
        addUnit(Unit(R.string.unit_group_energy_kwh, UnitSystem.METRIC, "kWh", "1000 Wh", this))
        addUnit(Unit(R.string.unit_group_energy_cal, UnitSystem.CALORIES, "cal", "4.1868 J", this))
        addUnit(Unit(R.string.unit_group_energy_kcal, UnitSystem.CALORIES, "kcal", "1000 cal", this))
    }
}