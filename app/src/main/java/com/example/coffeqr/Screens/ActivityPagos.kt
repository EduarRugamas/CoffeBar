package com.example.coffeqr.Screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeqr.R
import com.example.coffeqr.Utils.toast
import kotlinx.android.synthetic.main.activity_pagos.*

class ActivityPagos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagos)

        btn_efectivo.setOnClickListener {
            toast("Pago Con efectivo")
            startActivity(Intent(this,Activity_Final::class.java))
        }

        btn_tarjeta.setOnClickListener{


            if (layout_tarjeta_expadible.visibility == View.VISIBLE) {
                layout_tarjeta_expadible.visibility = View.GONE

            } else {
                layout_tarjeta_expadible.visibility = View.VISIBLE
                layout_tarjeta_expadible.animation = AnimationUtils.loadAnimation(this, R.anim.slide_down)

            }

        }

        if (numero_tarjeta == null && edi_text_name == null && editext_cvv == null && editext_fecha == null){
            toast("Los Campos estan vacios")
        }else {
            button_realizar_pago.setOnClickListener {
                toast("Has realizado el pago correctamente")
                startActivity(Intent(this, Activity_Final::class.java))
            }
        }


    }


}
