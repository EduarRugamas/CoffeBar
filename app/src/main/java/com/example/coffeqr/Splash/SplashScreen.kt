package com.example.coffeqr.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed
import com.example.coffeqr.Screens.ActivityEscanQR
import com.example.coffeqr.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


         Handler().postDelayed({
            startActivity(Intent(this, ActivityEscanQR::class.java))
            finish()
        }, 5000)
    }
}