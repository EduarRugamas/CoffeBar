package com.example.coffeqr.Screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.coffeqr.Class.DataListPostres
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.activity_coffe_details.*

class ItemPostresDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_postres_details)

        parseReceivedPostres()?.let {
            loadPostresData(it)
        }
    }

    private fun loadPostresData(it: DataListPostres) {
        nameDetails?.text = it.nombre
        precioDetails?.text = it.getDisplayPrice()
        Glide.with(this).load(it.imagen).into(imageDetails)

    }

    private fun parseReceivedPostres(): DataListPostres? = intent.getParcelableExtra(POSTRE_KEY)

    companion object {
        const val POSTRE_KEY = "item.key"
    }
}