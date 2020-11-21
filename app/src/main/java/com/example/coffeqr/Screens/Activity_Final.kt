package com.example.coffeqr.Screens


import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeqr.R
import com.example.coffeqr.Utils.toast
import kotlinx.android.synthetic.main.activity_final.*
import java.util.*
import java.util.concurrent.TimeUnit

class Activity_Final : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        val duration: Long = TimeUnit.MINUTES.toMillis(1)

        val timer = object : CountDownTimer(duration, 1000){
            override fun onTick(p0: Long) {

                val tiempo: String = String.format(Locale.ENGLISH, "%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(p0),
                        TimeUnit.MILLISECONDS.toSeconds(p0) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(p0))
                        )

                temporizador.text = tiempo
            }

            override fun onFinish() {
                toast("Tu orden en camino y puedes Cerrar la Aplicacion")

            }

        }
        timer.start()

    }

}