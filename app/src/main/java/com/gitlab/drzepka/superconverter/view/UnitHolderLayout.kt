package com.gitlab.drzepka.superconverter.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.unit.base.*
import com.gitlab.drzepka.superconverter.unit.base.Unit

class UnitHolderLayout : FrameLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val name by lazy { findViewById<TextView>(R.id.unit_list_item_name) }
    private val system by lazy { findViewById<TextView>(R.id.unit_list_item_system) }

    var currentType: UnitType = UnitType.LENGTH

    var unit: BaseUnit = Unit(0, UnitSystem.METRIC, "@", null, object : BaseUnitGroup() {})
        set(value) {
            name.setText(value.unitName)
            if (value.system != UnitSystem.DEFAULT)
                system.setText(value.system.systemName)
            else
                system.setText(currentType.unitName)
            field = value
        }
}