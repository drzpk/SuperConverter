package com.gitlab.drzepka.superconverter.unit.base

import android.support.annotation.StringRes
import com.gitlab.drzepka.superconverter.R

enum class UnitCategory(@StringRes val systemName: Int) {
    METRIC(R.string.unit_group_category_metric),
    ENGLISH(R.string.unit_group_category_english)
}
