package com.gitlab.drzepka.superconverter

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gitlab.drzepka.superconverter.unit.base.BaseUnitGroup
import com.gitlab.drzepka.superconverter.unit.base.Unit

class ChooseUnitDialog : DialogFragment(), AdapterView.OnItemClickListener {

    private lateinit var list: ListView
    private lateinit var adapter: DialogListAdapter
    private lateinit var onChooseListener: (unit: Unit) -> kotlin.Unit

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
     * @param unitGroup grupa jednostek, z której ma być wygenerowana lista
     * @param excluded aktualnie wybrana jednostka, która ma być wykluczona z listy
     * @param onChoose metoda, która będzie wywołana, gdy jednostka zostanie wybrana
     */
    fun show(activity: Activity, unitGroup: BaseUnitGroup, excluded: Unit, onChoose: (unit: Unit) -> kotlin.Unit) {
        adapter = DialogListAdapter()
        adapter.list.addAll(unitGroup.units.filterNot { it.unitName == excluded.unitName && it.symbol == excluded.symbol })

        onChooseListener = onChoose
        show(activity.fragmentManager, "ChooseUnitDialog")
    }

    private inner class DialogListAdapter : BaseAdapter() {

        val list = ArrayList<Unit>()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = if (convertView == null) {
                val inflater = LayoutInflater.from(activity)
                inflater.inflate(R.layout.unit_list_item, parent, false)
            }
            else
                convertView

            val name = view.findViewById<TextView>(R.id.unit_list_item_name)
            val category = view.findViewById<TextView>(R.id.unit_list_item_category)

            val item = getItem(position)
            name.setText(item.unitName)
            category.setText(item.category.systemName)

            return view
        }

        override fun getItem(position: Int): Unit = list[position]
        override fun getItemId(position: Int): Long = 0
        override fun getCount(): Int = list.size
    }
}