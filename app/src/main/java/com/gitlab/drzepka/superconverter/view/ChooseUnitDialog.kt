package com.gitlab.drzepka.superconverter.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gitlab.drzepka.superconverter.R
import com.gitlab.drzepka.superconverter.Utils
import com.gitlab.drzepka.superconverter.unit.base.BaseUnit
import com.gitlab.drzepka.superconverter.unit.base.UnitSystem
import com.gitlab.drzepka.superconverter.unit.base.UnitType

class ChooseUnitDialog : DialogFragment(), AdapterView.OnItemClickListener {

    private lateinit var list: ListView
    private lateinit var adapter: DialogListAdapter
    private lateinit var onChooseListener: (unit: BaseUnit) -> kotlin.Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // tworzenie widoku
        val layout = FrameLayout(activity)
        val padding = Utils.dpToPx(15f).toInt()
        list = ListView(activity)
        list.setPadding(padding, padding, padding, 0)
        list.onItemClickListener = this
        list.adapter = adapter
        layout.addView(list)

        // tworzenie dialogu
        return AlertDialog.Builder(activity)
                .setTitle(R.string.choose_unit_dialog_title)
                .setNegativeButton(android.R.string.cancel, { dialog, _ -> dialog.dismiss() })
                .setView(layout)
                .create()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        dismiss()
        onChooseListener(adapter.getItem(position))
    }

    /**
     * Wyświetla dialog.
     * @param activity aktywność, z której został wywołany dialog
     * @param unitType typ jednostek, z którego ma być wygenerowana lista
     * @param excluded aktualnie wybrana jednostka, która ma być wykluczona z listy
     * @param onChoose metoda, która będzie wywołana, gdy jednostka zostanie wybrana
     */
    fun show(activity: Activity, unitType: UnitType, excluded: BaseUnit, onChoose: (unit: BaseUnit) -> kotlin.Unit) {
        val list = ArrayList<BaseUnit>()
        list.addAll(unitType.unitGroup.units.filterNot { it.unitName == excluded.unitName && it.symbol == excluded.symbol })
        adapter = DialogListAdapter(list, unitType)

        onChooseListener = onChoose
        show(activity.fragmentManager, "ChooseUnitDialog")
    }

    private inner class DialogListAdapter(val list: ArrayList<BaseUnit>, val unitType: UnitType) : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = if (convertView == null) {
                val inflater = LayoutInflater.from(activity)
                inflater.inflate(R.layout.unit_list_item, parent, false)
            }
            else
                convertView

            val name = view.findViewById<TextView>(R.id.unit_list_item_name)
            val category = view.findViewById<TextView>(R.id.unit_list_item_system)

            val item = getItem(position)
            name.setText(item.unitName)
            if (item.system != UnitSystem.DEFAULT)
                category.setText(item.system.systemName)
            else
                category.setText(unitType.unitName)

            return view
        }

        override fun getItem(position: Int): BaseUnit = list[position]
        override fun getItemId(position: Int): Long = 0
        override fun getCount(): Int = list.size
    }
}