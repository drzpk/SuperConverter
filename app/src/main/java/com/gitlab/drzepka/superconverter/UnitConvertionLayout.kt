package com.gitlab.drzepka.superconverter

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitCategory

class UnitConvertionLayout : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val text by lazy { findViewById<EditText>(R.id.unit_convertion_layout_text) }
    private val unitText by lazy { findViewById<TextView>(R.id.unit_convertion_layout_unit) }

    var value = 0.0
        set(value) {
            text.setText(value.toString())
            field = value
        }
    var unit = Unit(0, UnitCategory.METRIC, "@", null, object : BaseUnitGroup() {})
        set(value) {
            unitText.text = value.symbol
            field = value
        }
}