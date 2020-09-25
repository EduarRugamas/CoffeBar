package com.example.coffeqr.Screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coffeqr.Clases.ViewPageAdapter
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.activity_tab_menu.*

class TabMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_menu)


        toolBar.title = "Menu Coffe y Postres"
        setSupportActionBar(toolBar)

        val fragAdapter = ViewPageAdapter(supportFragmentManager)
        viewPager.adapter = fragAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}