package com.example.coffeqr.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
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
        bton_detalles()
    }

    private fun setUpTabs(){

        val adapter = ViewPageAdapter(supportFragmentManager)

        adapter.addFragment(Coffe_Fragment(), "Cafes")
        adapter.addFragment(desserts_Fragment(), "Postres")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.icons_coffe)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.icons_cokie)
    }

    private fun bton_detalles(){

        btn_orden_pedidos.setOnClickListener {
            val intent = Intent(this, OrdenPedidos::class.java)
            startActivity(intent)
            layout_tab_menu.animation = AnimationUtils.loadAnimation(this,R.anim.slide_anim)
        }
    }

}