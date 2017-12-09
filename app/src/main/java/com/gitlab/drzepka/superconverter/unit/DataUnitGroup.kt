package com.gitlab.drzepka.superconverter.unit

import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class DataUnitGroup : BaseUnitGroup() {

    init {
        addUnit(Unit(R.string.unit_group_data_bit, UnitSystem.DEFAULT, "b", null, this))
        addUnit(Unit(R.string.unit_group_data_byte, UnitSystem.DEFAULT, "B", "8 b", this))

        addUnit(Unit(R.string.unit_group_data_kbyte, UnitSystem.DECIMAL, "kB", "1000 B", this))
        addUnit(Unit(R.string.unit_group_data_mbyte, UnitSystem.DECIMAL, "MB", "1000 kB", this))
        addUnit(Unit(R.string.unit_group_data_gbyte, UnitSystem.DECIMAL, "GB", "1000 MB", this))
        addUnit(Unit(R.string.unit_group_data_tbyte, UnitSystem.DECIMAL, "TB", "1000 GB", this))
        addUnit(Unit(R.string.unit_group_data_pbyte, UnitSystem.DECIMAL, "PB", "1000 TB", this))

        addUnit(Unit(R.string.unit_group_data_kibyte, UnitSystem.BINARY, "KiB", "1024 B", this))
        addUnit(Unit(R.string.unit_group_data_mibyte, UnitSystem.BINARY, "MiB", "1024 KiB", this))
        addUnit(Unit(R.string.unit_group_data_gibyte, UnitSystem.BINARY, "GiB", "1024 MiB", this))
        addUnit(Unit(R.string.unit_group_data_tibyte, UnitSystem.BINARY, "TiB", "1024 GiB", this))
        addUnit(Unit(R.string.unit_group_data_pibyte, UnitSystem.BINARY, "PiB", "1024 TiB", this))
    }
}