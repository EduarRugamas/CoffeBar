package com.example.coffeqr.Screens

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
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_orden_pedidos.*
import kotlinx.android.synthetic.main.activity_orden_pedidos.shimmer_container
import kotlinx.android.synthetic.main.fragment_coffe.*

class OrdenPedidos : AppCompatActivity(), AdapterPedidos.onClicDeleteItem {

        private val listaPedidos: ArrayList<DataPedidos> = ArrayList()
        private  val AdapterP = AdapterPedidos(listaPedidos, this)
        private  val dbPedidos = FirebaseFirestore.getInstance()

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

        },5000)



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

    override fun onClickDeleteItem(id: String) {

        AlertDialog.Builder(this)
            .setTitle("Eliminar de la lista de pedido")
            .setMessage("Desea eliminar este pedido")
            .setNegativeButton(R.string.no) { _,_ -> finish() }
            .setPositiveButton(R.string.si){ _,_ -> eliminarItem(id) }
            .create()
            .show()

    }
    private fun eliminarItem(idP:String){


        dbPedidos.collection("Pedidos")
            .document(idP)
            .delete().addOnSuccessListener {
                toast("Item eliminado correctamente")
            }.addOnFailureListener {
                toast("No se pudo eliminar este Item")
            }

        Log.d("id de parametro", idP)

        updateDataRecyclerView()
    }
    private fun updateDataRecyclerView(){
        dbPedidos.collection("Pedidos").addSnapshotListener { result, error ->
            if (error !=  null) toast("No se a podido actualizar la lista")

            listaPedidos.clear()

            result?.forEach {
                val id = it.id
                val imagen = it.getString("Imagen")
                val mesa = it.getString("Mesa")
                val nombre = it.getString("Nombre")
                val cantidad = it.getLong("Cantidad")
                val precio = it.getString("Precio")

                if (imagen != null && mesa != null &&nombre != null &&cantidad != null && precio != null){
                    listaPedidos.add(DataPedidos(id,imagen,mesa,nombre,cantidad.toString(),precio))
                }
                Log.d("id de update", id)
            }
            AdapterP.notifyDataSetChanged()
        }
    }




}