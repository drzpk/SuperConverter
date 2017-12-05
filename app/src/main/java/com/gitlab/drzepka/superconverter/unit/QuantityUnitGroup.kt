package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class QuantityUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_quantity_piece, UnitSystem.DEFAULT, "szt", null, this))
        addUnit(Unit(R.string.unit_group_quantity_dozen, UnitSystem.DEFAULT, "tuz", "12 szt", this))
        addUnit(Unit(R.string.unit_group_quantity_mendel, UnitSystem.DEFAULT, "men", "15 szt", this))
        addUnit(Unit(R.string.unit_group_quantity_cmendel, UnitSystem.DEFAULT, "cmen", "16 szt", this))
        addUnit(Unit(R.string.unit_group_quantity_kopa, UnitSystem.DEFAULT, "kop", "60 szt", this))
        addUnit(Unit(R.string.unit_group_quantity_gros, UnitSystem.DEFAULT, "gros", "144 szt", this))
    }
}