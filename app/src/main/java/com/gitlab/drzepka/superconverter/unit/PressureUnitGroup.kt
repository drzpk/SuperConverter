package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class PressureUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_pressure_pascal, UnitSystem.DEFAULT, "Pa", null, this))
        addUnit(Unit(R.string.unit_group_pressure_hpascal, UnitSystem.DEFAULT, "hPa", "100 Pa", this))
        addUnit(Unit(R.string.unit_group_pressure_bar, UnitSystem.DEFAULT, "bar", "1000 hPa", this))
        addUnit(Unit(R.string.unit_group_pressure_npermm2, UnitSystem.DEFAULT, "N/mm2", "10 bar", this))
        addUnit(Unit(R.string.unit_group_pressure_kgperm2, UnitSystem.DEFAULT, "kg/m2", "9.8067 Pa", this))
        addUnit(Unit(R.string.unit_group_pressure_mmhg, UnitSystem.DEFAULT, "mmHg", "1.3332 hPa", this))
        addUnit(Unit(R.string.unit_group_pressure_psi, UnitSystem.DEFAULT, "lbf/in2", "68.9476 hPa", this))
    }
}