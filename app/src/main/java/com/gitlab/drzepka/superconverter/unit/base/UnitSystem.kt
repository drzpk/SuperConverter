package com.gitlab.drzepka.superconverter.unit.base

import android.support.annotation.StringRes
import com.gitlab.drzepka.superconverter.R

enum class UnitSystem(@StringRes val systemName: Int) {
    DEFAULT(R.string.unit_group_system_default),
    METRIC(R.string.unit_group_system_metric),
    ENGLISH(R.string.unit_group_system_english),
    DAY_OR_LESS(R.string.unit_group_system_less_than_day),
    MORE_THAN_DAY(R.string.unit_group_system_day_or_more),
    CALORIES(R.string.unit_group_system_calories)
}
