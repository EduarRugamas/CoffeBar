package com.example.coffeqr.Screens

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.coffeqr.Class.DataListCoffe
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.activity_coffe_details.*
import kotlinx.android.synthetic.main.activity_postres_details.*


class ItemCoffeDetails : AppCompatActivity() {

    private var cantidaProductoCafes = 0
    private var nombreDeCafe = ""
    private lateinit var prefCafe: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffe_details)

        prefCafe = getSharedPreferences("PREFSCAFE", Context.MODE_PRIVATE)

        parseReceivedCoffe()?.let {
            loadCoffeData(it)
            ViewButons()
            MostrarBotonProductos()
        }

    }

    private fun ViewButons(){
        btn_mas.setOnClickListener {
            addCoffeProduct()
        }
        btn_menos.setOnClickListener {
            reduceCoffeProduct()
        }
        addButton_cafes.setOnClickListener {
            guardarDatosOrden()
        }
        mostrarItemCafes.setOnClickListener {
            mostrarData()
        }
    }
    private fun loadCoffeData(it: DataListCoffe) {
        nameDetails_cafes?.text = it.nombre
        precioDetails_cafes?.text = it.getDisplayPrice()
        Glide.with(this).load(it.imagen).into(imageDetails_cafes)

    }
    private fun addCoffeProduct() {
        cantidaProductoCafes += 1
        cantidad_producto.text = cantidaProductoCafes.toString()
        MostrarBotonProductos()
    }

    private fun reduceCoffeProduct() {
        if (cantidaProductoCafes > 0) {
            cantidaProductoCafes -= 1
            cantidad_producto.text = cantidaProductoCafes.toString()
        }
        MostrarBotonProductos()
    }

    private fun MostrarBotonProductos() {
        addButton_cafes.visibility = if (cantidaProductoCafes == 0) View.GONE else View.VISIBLE
        addButton_cafes.text = getString(R.string.add_to_cart, cantidaProductoCafes)
    }
    private fun guardarDatosOrden(){
        val editor: SharedPreferences.Editor = prefCafe.edit()
        nombreDeCafe = nameDetails_cafes.text.toString()
        editor.putString(KEY_CAFES, nombreDeCafe)
        editor.putInt(KEY_INT_CAFES, cantidaProductoCafes)
        editor.apply()
        Log.d("nombre guardada", nombreDeCafe)
        Log.d("cantida ", cantidaProductoCafes.toString())
    }
    private fun mostrarData(){
        val myPrefs = prefCafe.getString(KEY_CAFES,"")
        val prefs2 = prefCafe.getInt(KEY_INT_CAFES,0)

        Log.d("nombre data G", myPrefs.toString())
        Log.d("cantidad data G", prefs2.toString())

    }


    private fun parseReceivedCoffe(): DataListCoffe? = intent.getParcelableExtra(COFFE_KEY)

    companion object {
        const val COFFE_KEY = "coffe.key"
        const val KEY_CAFES = "key.cafes"
        const val KEY_INT_CAFES = "key.int.cafes"
    }
}