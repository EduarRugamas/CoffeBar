package com.example.coffeqr.Screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.activity_pagos.*

class ActivityPagos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagos)

        btn_tarjeta.setOnClickListener{
            if (layout_tarjeta_expadible.visibility == View.VISIBLE) {
                layout_tarjeta_expadible.visibility = View.GONE
            } else {
                layout_tarjeta_expadible.visibility = View.VISIBLE
            }
        }



    }


}
