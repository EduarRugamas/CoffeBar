package com.example.coffeqr.Screens


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeqr.Adapters.AdapterPedidos
import com.example.coffeqr.Class.DataPedidos
import com.example.coffeqr.R
import com.example.coffeqr.Utils.toast
import com.google.android.material.animation.AnimationUtils
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_orden_pedidos.*
import kotlinx.android.synthetic.main.activity_orden_pedidos.shimmer_container

@Suppress("DEPRECATION")
class OrdenPedidos : AppCompatActivity(){

        private val listaPedidos: ArrayList<DataPedidos> = ArrayList()
        private  val AdapterP = AdapterPedidos(listaPedidos)
        val dbPedidos = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orden_pedidos)

        Handler().postDelayed({
            shimmer_container.startShimmer()
            shimmer_container.visibility = View.VISIBLE
            shimmer_container.stopShimmer()
            shimmer_container.visibility = View.GONE

            rc_pedidos.layoutManager = LinearLayoutManager(this)
            rc_pedidos.adapter = AdapterP
            loadRecyclerViewBD()

        },8000)


        btn_pagar.setOnClickListener {
            startActivity(Intent(this, ActivityPagos::class.java))
            layout_orden_pedidos.animation = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.slide_anim)
        }

    }

    private fun loadRecyclerViewBD(){
        dbPedidos.collection("Pedidos").get().addOnSuccessListener { result ->
            if (result != null){
                listaPedidos.clear()
                for (documentos in result){
                    val id = documentos.id
                    val imagen = documentos.getString("Imagen")
                    val mesa = documentos.getString("Mesa")
                    val nombre = documentos.getString("Nombre")
                    val cantidad = documentos.getLong("Cantidad")
                    val precio = documentos.getString("Precio")

                    if (imagen != null && mesa != null && nombre != null && cantidad != null && precio != null){
                        listaPedidos.add(DataPedidos(id, imagen, mesa, nombre, cantidad.toString(), precio))
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