package com.example.coffeqr.Screens

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeqr.R
import com.example.coffeqr.Utils.toast
import com.google.protobuf.Empty
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

        button_realizar_pago.setOnClickListener {
            val Ntarjeta = numero_tarjeta.text
            val nombre = edi_text_name.text
            val fecha = editext_fecha.text
            val cvv = editext_cvv.text
            if (TextUtils.isEmpty(Ntarjeta) && TextUtils.isEmpty(nombre) && TextUtils.isEmpty(fecha) && TextUtils.isEmpty(cvv) ){
                numero_tarjeta.error = "Campo vacio"
                edi_text_name.error = "Campo vacio"
                editext_fecha.error = "Campo vacio"
                editext_cvv.error = "Campo vacio"
            }else{
                toast("Pago realizado correctamente")
                startActivity(Intent(this,Activity_Final::class.java))
            }
        }



    }


}
