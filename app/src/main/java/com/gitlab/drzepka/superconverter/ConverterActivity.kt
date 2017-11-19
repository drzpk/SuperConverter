package com.gitlab.drzepka.superconverter

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gitlab.drzepka.superconverter.unit.base.UnitType

class ConverterActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var upperHolder: FrameLayout
    private lateinit var lowerHolder: FrameLayout
    private lateinit var leftUnit: UnitConversionLayout
    private lateinit var rightUnit: UnitConversionLayout

    private lateinit var drawerAdapter: DrawerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        drawerLayout = findViewById(R.id.converter_drawer)
        val list = findViewById<ListView>(R.id.converter_drawer_list)
        drawerAdapter = DrawerAdapter()
        list.adapter = drawerAdapter
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, android.R.string.unknownName, android.R.string.unknownName)
        drawerLayout.addDrawerListener(drawerToggle)

        leftUnit = findViewById(R.id.converter_unit_conversion_left)
        rightUnit = findViewById(R.id.converter_unit_conversion_right)

        upperHolder = findViewById(R.id.converter_unit_holder_1)
        lowerHolder = findViewById(R.id.converter_unit_holder_2)
        upperHolder.setOnClickListener { onHolderClick(true) }
        lowerHolder.setOnClickListener { onHolderClick(false) }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (drawerToggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }

    /**
     * Wywoływana, gdy przycisk (strzałki) do zmiany kolejności jednostek zostanie wciśnięty.
     */
    @Suppress("UNUSED_PARAMETER")
    fun swapUnits(view: View) {

    }

    /**
     * Wywoływana w momencie kliknięcia jednego z widoków z nazwą jednostki.
     */
    private fun onHolderClick(upper: Boolean) {

    }

    /**
     * Wywoływana, gdy kategoria jednostek zostanie wybrana.
     */
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    inner class DrawerAdapter : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: layoutInflater.inflate(R.layout.drawer_list_item, parent, false)
            val item = getItem(position)

            view.findViewById<ImageView>(R.id.drawer_list_item_image).setImageResource(item.unitIcon)
            view.findViewById<TextView>(R.id.drawer_list_item_text).setText(item.unitName)
            return view
        }

        override fun getItem(position: Int): UnitType = UnitType.values()[position]
        override fun getItemId(position: Int): Long = 0L
        override fun getCount(): Int = UnitType.values().size
    }
}