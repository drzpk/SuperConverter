package com.gitlab.drzepka.superconverter.unit.base

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.*

enum class UnitType(@StringRes val unitName: Int, @DrawableRes val unitIcon: Int, val unitGroup: BaseUnitGroup? = null) {
    PRESSURE(R.string.unit_type_pressure, R.drawable.unit_type_pressure, PressureUnitGroup()),
    TIME(R.string.unit_type_time, R.drawable.unit_type_time, TimeUnitGroup()),
    LENGTH(R.string.unit_type_length, R.drawable.unit_type_length, LengthUnitGroup()),
    ENERGY(R.string.unit_type_energy, R.drawable.unit_type_energy, EnergyUnitGroup()),
    QUANTITY(R.string.unit_type_quantity, R.drawable.unit_type_quantity, QuantityUnitGroup()),
    MASS(R.string.unit_type_mass, R.drawable.unit_type_mass, MassUnitGroup()),
    VOLUME(R.string.unit_type_volume, R.drawable.unit_type_volume, VolumeUnitGroup()),
    AREA(R.string.unit_type_area, R.drawable.unit_type_area, AreaUnitGroup()),
    SPEED(R.string.unit_type_speed, R.drawable.unit_type_speed, SpeedUnitGroup()),
    DATA(R.string.unit_type_data, R.drawable.unit_type_data, DataUnitGroup()),
    TEMPERATURE(R.string.unit_type_temperature, R.drawable.unit_type_temperature, TemperatureUnitGroup()),
    CURRENCY(R.string.unit_type_currency, R.drawable.unit_type_currency, CurrencyUnitGroup())
}