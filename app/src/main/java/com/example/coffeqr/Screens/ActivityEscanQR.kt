package com.example.coffeqr.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.activity_escan_qr.*


class ActivityEscanQR : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escan_qr)


        btn.setOnClickListener{
            startActivity(Intent(this, TabMenu::class.java))
        }

    }
}