package com.gitlab.drzepka.superconverter.unit.base

import android.support.annotation.StringRes
import com.gitlab.drzepka.superconverter.R

enum class UnitSystem(@StringRes val systemName: Int) {
    METRIC(R.string.unit_group_system_metric),
    ENGLISH(R.string.unit_group_system_english)
}
