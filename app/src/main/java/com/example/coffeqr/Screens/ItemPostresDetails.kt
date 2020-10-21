package com.example.coffeqr.Screens

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.coffeqr.Class.DataListPostres
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.activity_postres_details.*


class ItemPostresDetails : AppCompatActivity() {

    private var cantidaProductoPostre = 0
    private var nombreDePostre = ""
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postres_details)

        prefs = getSharedPreferences("PREFSPOSTRE", Context.MODE_PRIVATE)


        parseReceivedPostres()?.let {
            loadPostresData(it)
            ViewButtons()
            MostrarBotonProductos()
        }
    }

    private fun ViewButtons(){
        btn_mas_postre.setOnClickListener {
            agregarItemCantidad()
        }

        btn_menos_postre.setOnClickListener {
            eliminarItemCantidad()
        }

        agregarItem.setOnClickListener {
            guardarDatosOrden()
        }

        mostrarItem.setOnClickListener {
            mostrarData()
        }

    }

    private fun agregarItemCantidad(){
        cantidaProductoPostre += 1
        cantidad_producto_postre.text = cantidaProductoPostre.toString()
        MostrarBotonProductos()
    }
    private fun eliminarItemCantidad(){
        if (cantidaProductoPostre > 0) {
            cantidaProductoPostre -= 1
            cantidad_producto_postre.text = cantidaProductoPostre.toString()
        }
        MostrarBotonProductos()
    }
    private fun MostrarBotonProductos() {
        agregarItem.visibility = if (cantidaProductoPostre == 0) View.GONE else View.VISIBLE
        agregarItem.text = getString(R.string.add_to_cart, cantidaProductoPostre)
    }

    private fun loadPostresData(it: DataListPostres) {
        nameDetailsPostre?.text = it.nombre
        precioDetailsPostre?.text = it.getDisplayPrice()
        Glide.with(this).load(it.imagen).into(imageDetailsPostre)

    }


    private fun guardarDatosOrden(){
        val editor: SharedPreferences.Editor = prefs.edit()
        nombreDePostre = nameDetailsPostre.text.toString()
        editor.putString(KEY_POSTRES, nombreDePostre)
        editor.putInt(KEY_INT_POSTRES, cantidaProductoPostre)
        editor.apply()
        Log.d("nombre postre guardada", nombreDePostre)
        Log.d("cantida postre", cantidaProductoPostre.toString())
    }


    private fun mostrarData(){
        val myPrefs = prefs.getString(KEY_POSTRES,"")
        val prefs2 = prefs.getInt(KEY_INT_POSTRES,0)

        Log.d("nombre data G", myPrefs.toString())
        Log.d("cantidad data G", prefs2.toString())

    }





    private fun parseReceivedPostres(): DataListPostres? = intent.getParcelableExtra(POSTRE_KEY)

    companion object {
        const val POSTRE_KEY = "item.key"
        const val KEY_POSTRES = "key.preferences"
        const val KEY_INT_POSTRES = "key.int.postres"
    }
}