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
import com.example.coffeqr.Utils.toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_coffe_details.*
import kotlinx.android.synthetic.main.activity_postres_details.*
import kotlin.math.roundToInt


class ItemCoffeDetails : AppCompatActivity() {

    private var cantidaProductoCafes = 0
    private var nombreDeCafe = ""
    private var Imagen = ""
    private var precio = ""
    private var db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffe_details)

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
            SaveDataCofesDB()
        }

    }
    private fun loadCoffeData(it: DataListCoffe) {
        nameDetails_cafes?.text = it.nombre
        precioDetails_cafes?.text = it.getDisplayPrice()
        Glide.with(this).load(it.imagen).into(imageDetails_cafes)
        Imagen = it.imagen
        precio = it.getDisplayPrice()

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
        addButton_cafes.text = getString(R.string.mostrar_Dolar, cantidaProductoCafes)
    }

    private fun SaveDataCofesDB(){
        nombreDeCafe = nameDetails_cafes.text.toString()

        val dataCafes = hashMapOf(
            "Mesa" to "mesa 1" ,
            "Nombre" to nombreDeCafe ,
            "Cantidad" to cantidaProductoCafes,
            "Precio" to precio,
            "Imagen" to Imagen
        )

        db.collection("Pedidos")
            .add(dataCafes)
            .addOnCompleteListener { result ->
                if (result.isSuccessful ) toast("Pedido añadido correctamente..!!")

            }
            .addOnFailureListener { fallo ->
                toast("No se pudo añadir el pedido")
            }
        finish()
    }


    private fun parseReceivedCoffe(): DataListCoffe? = intent.getParcelableExtra(COFFE_KEY)

    companion object {
        const val COFFE_KEY = "coffe.key"
        const val KEY_CAFES = "key.cafes"
        const val KEY_INT_CAFES = "key.int.cafes"
    }
}