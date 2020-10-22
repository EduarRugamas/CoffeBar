package com.example.coffeqr.Screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeqr.Adapters.AdapterPedidos
import com.example.coffeqr.Class.DataPedidos
import com.example.coffeqr.R
import com.example.coffeqr.Utils.toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_orden_pedidos.*

class OrdenPedidos : AppCompatActivity() {

     private val listaPedidos: ArrayList<DataPedidos> = ArrayList()
     private val AdapterP = AdapterPedidos(listaPedidos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orden_pedidos)

         val dbPedidos = FirebaseFirestore.getInstance()
        rc_pedidos.layoutManager = LinearLayoutManager(this)
        rc_pedidos.adapter = AdapterP

        dbPedidos.collection("Pedidos").get().addOnSuccessListener {result ->
                if (result != null){
                    listaPedidos.clear()
                    for (documentos in result){
                        val imagen = documentos.getString("Imagen")
                        val mesa = documentos.getString("Mesa")
                        val nombre = documentos.getString("Nombre")
                        val cantidad = documentos.getLong("Cantidad")
                        val precio = documentos.getString("Precio")

                        if (imagen != null && mesa != null &&nombre != null &&cantidad != null && precio != null){
                            listaPedidos.add(DataPedidos(imagen,mesa,nombre,cantidad,precio))
                        }

                    }
                    AdapterP.notifyDataSetChanged()

                }
        }.addOnFailureListener {
            toast("No se pudo obtener los datos!!...")
            Log.d("Error", it.toString())
        }
    }
}