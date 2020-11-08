package com.example.coffeqr.Adapters

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeqr.Class.DataPedidos
import com.example.coffeqr.R
import com.example.coffeqr.Utils.toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*
import kotlinx.android.synthetic.main.item_pedidos.view.*

class AdapterPedidos
constructor(
    val ListaPedidos: ArrayList<DataPedidos>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val dbPedidos = FirebaseFirestore.getInstance()
    private val listaPedidos: ArrayList<DataPedidos> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pedidos, parent, false)
        return PedidosViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PedidosViewHolder).bind(ListaPedidos[position])
    }

    override fun getItemCount(): Int = ListaPedidos.size


    inner class PedidosViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(Lpedidos: DataPedidos) {
            val idpedidos = Lpedidos.idPe
            itemView.ItemMesa.text = Lpedidos.Mesa
            itemView.nombreItem.text = Lpedidos.Nombre
            itemView.precioItem.text = Lpedidos.Precio
            itemView.ItemCantidad.text = Lpedidos.Cantidad.toString()
            Glide.with(itemView.context).load(Lpedidos.Imagen).into(itemView.ItemImagen)
            Log.d("idViewHolder", idpedidos)

            itemView.text_eliminar_item.setOnClickListener {
                val view = View.inflate(itemView.context, R.layout.custom_alert_dialog, null)
                val builder = AlertDialog.Builder(itemView.context)
                builder.setView(view)
                val dialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                view.Cancelar.setOnClickListener {
                    dialog.dismiss()
                }
                view.eliminar.setOnClickListener {
                    eliminaritem(idpedidos)
                }
            }
        }

        fun eliminaritem(id:String){
            dbPedidos.collection("Pedidos")
                    .document(id)
                    .delete().addOnSuccessListener {
                        Toast.makeText(itemView.context, "Item eliminado correctamente", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(itemView.context, "No se pudo eliminar este Item", Toast.LENGTH_SHORT).show()

                    }

            //Log.d("id de parametro", idP)

            updateDataRecyclerView()
        }
        fun updateDataRecyclerView(){
            dbPedidos.collection("Pedidos").addSnapshotListener { result, error ->
                if (error !=  null) Toast.makeText(itemView.context, "No se a podido actualizar la lista", Toast.LENGTH_SHORT).show()

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
                    //Log.d("id de update", id)
                }
                notifyDataSetChanged()
            }
        }

    }




}