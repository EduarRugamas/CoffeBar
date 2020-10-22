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
import com.example.coffeqr.Utils.toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_postres_details.*


class ItemPostresDetails : AppCompatActivity() {

    private var cantidaProductoPostre = 0
    private var nombreDePostre = ""
    private var image = ""
   private var precio = ""
    private val dbP = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postres_details)




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
            SavesetDataBD()
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
        agregarItem.text = getString(R.string.mostrar_Dolar, cantidaProductoPostre)
    }

    private fun loadPostresData(it: DataListPostres) {
        nameDetailsPostre?.text = it.nombre
        precioDetailsPostre?.text = it.getDisplayPrice()
        Glide.with(this).load(it.imagen).into(imageDetailsPostre)

        image = it.imagen
        precio = it.getDisplayPrice()

    }


    //envia los datos a la base de datos en firebase
   private fun SavesetDataBD(){
       nombreDePostre = nameDetailsPostre.text.toString()
       val dataPostres = hashMapOf(
           "Mesa" to "mesa 1",
           "Nombre" to nombreDePostre,
           "Cantidad" to cantidaProductoPostre,
           "Imagen" to image,
           "Precio" to precio
       )

       dbP.collection("Pedidos")
           .add(dataPostres)
           .addOnCompleteListener { result ->
               if (result.isSuccessful) toast("pedido añadido correctamente!!..")
           }.addOnFailureListener { fallo ->
               toast("Fallo al añadir el pedido!!...")
               Log.e("Error DB", fallo.toString())
           }

        finish()
   }





    private fun parseReceivedPostres(): DataListPostres? = intent.getParcelableExtra(POSTRE_KEY)

    companion object {
        const val POSTRE_KEY = "item.key"
        const val KEY_POSTRES = "key.preferences"
        const val KEY_INT_POSTRES = "key.int.postres"
    }
}