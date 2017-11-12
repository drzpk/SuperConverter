package com.gitlab.drzepka.superconverter

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.gitlab.drzepka.superconverter.unit.base.UnitType

class ConverterActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        drawerLayout = findViewById(R.id.converter_drawer)
        val list = findViewById<ListView>(R.id.converter_drawer_list)
        list.adapter = DrawerAdapter()
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, android.R.string.unknownName, android.R.string.unknownName)
        drawerLayout.addDrawerListener(drawerToggle)

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