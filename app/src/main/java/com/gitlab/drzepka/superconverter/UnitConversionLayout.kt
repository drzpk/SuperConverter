package com.gitlab.drzepka.superconverter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem

class UnitConversionLayout : LinearLayout, TextWatcher {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val text by lazy { findViewById<EditText>(R.id.unit_conversion_layout_text) }
    private val unitText by lazy { findViewById<TextView>(R.id.unit_conversion_layout_unit) }

    var value: Double = 0.0
        get() = if (text.length() > 0) text.text.toString().toDouble() else 0.0
        set(value) {
            text.setText(value.toString())
            field = value
        }
    var unit = Unit(0, UnitSystem.METRIC, "@", null, object : BaseUnitGroup() {})
        set(value) {
            unitText.text = value.symbol
            field = value
        }
    var disabled = false
        set(value) {
            text.isEnabled = !value
            field = value
        }
    var onChangeListener: (() -> kotlin.Unit)? = null
        set(value) {
            if (value != null) {
                text.removeTextChangedListener(this)
                text.addTextChangedListener(this)
            }
            field = value
        }

    override fun afterTextChanged(s: Editable?) {
        onChangeListener?.invoke()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
}
