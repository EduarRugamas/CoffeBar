package com.example.coffeqr.Screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coffeqr.Adapters.ViewPageAdapter
import com.example.coffeqr.Fragments.Coffe_Fragment
import com.example.coffeqr.Fragments.desserts_Fragment
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.activity_tab_menu.*

class TabMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_menu)
        setUpTabs()
    }

    private fun setUpTabs(){

        toolbar.title = getString(R.string.title_appbar_layout)
        setSupportActionBar(toolbar)
        val adapter = ViewPageAdapter(supportFragmentManager)

        adapter.addFragment(Coffe_Fragment(), "Cafes")
        adapter.addFragment(desserts_Fragment(), "Postres")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.icons_coffe)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.icons_cokie)
    }
}