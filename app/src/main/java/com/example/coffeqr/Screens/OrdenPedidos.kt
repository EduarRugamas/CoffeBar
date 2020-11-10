package com.example.coffeqr.Screens


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffeqr.Adapters.AdapterPedidos
import com.example.coffeqr.Class.DataPedidos
import com.example.coffeqr.R
import com.example.coffeqr.Utils.toast
import com.google.android.material.animation.AnimationUtils
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_orden_pedidos.*
import kotlinx.android.synthetic.main.activity_orden_pedidos.shimmer_container
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*

@Suppress("DEPRECATION")
class OrdenPedidos : AppCompatActivity(), AdapterPedidos.OnclickDeleteItem {

    private val listaPedidos: ArrayList<DataPedidos> = ArrayList()
    private val AdapterP = AdapterPedidos(listaPedidos, this)
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

        }, 8000)


        btn_pagar.setOnClickListener {
            startActivity(Intent(this, ActivityPagos::class.java))

        }

    }

    private fun loadRecyclerViewBD() {
        dbPedidos.collection("Pedidos").get().addOnSuccessListener { result ->
            if (result != null) {
                listaPedidos.clear()
                for (documentos in result) {
                    val id = documentos.id
                    val imagen = documentos.getString("Imagen")
                    val mesa = documentos.getString("Mesa")
                    val nombre = documentos.getString("Nombre")
                    val cantidad = documentos.getLong("Cantidad")
                    val precio = documentos.getString("Precio")

                    if (imagen != null && mesa != null && nombre != null && cantidad != null && precio != null) {
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
    override fun onClickDeleteItem(id: String, imagenPedido: String, Nombre: String) {
        val view = View.inflate(this, R.layout.custom_alert_dialog, null)
        val builder = android.app.AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        Glide.with(this).load(imagenPedido).into(view.image_delete)
        view.text_nombre_pedido.text = Nombre

        view.Cancelar.setOnClickListener {
            dialog.dismiss()
        }
        view.eliminar.setOnClickListener {
            eliminaritem(id)
            dialog.dismiss()

        }
    }


    private fun eliminaritem(id:String){
        dbPedidos.collection("Pedidos")
                .document(id)
                .delete().addOnSuccessListener {
                    Toast.makeText(this, "Pedido eliminado correctamente", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "No se pudo eliminar este Item", Toast.LENGTH_SHORT).show()

                }

        //Log.d("id de parametro", idP)
        updateDataRecyclerView()
    }

    private fun updateDataRecyclerView() {
        dbPedidos.collection("Pedidos").addSnapshotListener { result, error ->
            if (error != null) Toast.makeText(this, "No se a podido actualizar la lista", Toast.LENGTH_SHORT).show()

            listaPedidos.clear()

            result?.forEach {
                val id = it.id
                val imagen = it.getString("Imagen")
                val mesa = it.getString("Mesa")
                val nombre = it.getString("Nombre")
                val cantidad = it.getLong("Cantidad")
                val precio = it.getString("Precio")

                if (imagen != null && mesa != null && nombre != null && cantidad != null && precio != null) {
                    listaPedidos.add(DataPedidos(id, imagen, mesa, nombre, cantidad.toString(), precio))
                }
                //Log.d("id de update", id)
            }
             AdapterP.notifyDataSetChanged()
        }
    }




}