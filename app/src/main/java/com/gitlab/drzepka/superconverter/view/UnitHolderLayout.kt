package com.gitlab.drzepka.superconverter.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.BaseUnit
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class UnitHolderLayout : FrameLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val name by lazy { findViewById<TextView>(R.id.unit_list_item_name) }
    private val system by lazy { findViewById<TextView>(R.id.unit_list_item_system) }

    var unit: BaseUnit = Unit(0, UnitSystem.METRIC, "@", null, object : BaseUnitGroup() {})
        set(value) {
            name.setText(value.unitName)
            system.setText(value.system.systemName)
            field = value
        }
}