package com.example.coffeqr.Screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.coffeqr.Class.DataListCoffe
import com.example.coffeqr.Class.DataListPostres
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.activity_coffe_details.*

class ItemCoffeDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffe_details)

        parseReceivedCoffe()?.let {
            loadCoffeData(it)
        }



    }
    private fun loadCoffeData(it: DataListCoffe) {
        nameDetails?.text = it.nombre
        precioDetails?.text = it.getDisplayPrice()
        Glide.with(this).load(it.imagen).into(imageDetails)

    }



    private fun parseReceivedCoffe(): DataListCoffe? = intent.getParcelableExtra(COFFE_KEY)



    companion object {
        const val COFFE_KEY = "coffe.key"

    }
}