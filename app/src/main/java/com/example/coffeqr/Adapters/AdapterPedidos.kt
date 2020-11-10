package com.example.coffeqr.Adapters

import android.annotation.SuppressLint
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
    val ListaPedidos: ArrayList<DataPedidos>,
    val clickPedido: OnclickDeleteItem
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pedidos, parent, false)
        return PedidosViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PedidosViewHolder).bind(ListaPedidos[position])
    }

    override fun getItemCount(): Int = ListaPedidos.size


    inner class PedidosViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")

        fun bind(Lpedidos: DataPedidos) {
            val idpedidos = Lpedidos.idPe
            itemView.ItemMesa.text = Lpedidos.Mesa
            itemView.nombreItem.text = Lpedidos.Nombre
            itemView.precioItem.text = "$${Lpedidos.Precio}"
            itemView.ItemCantidad.text = Lpedidos.Cantidad.toString()
            Glide.with(itemView.context).load(Lpedidos.Imagen).into(itemView.ItemImagen)
            Log.d("idViewHolder", idpedidos)
            //val total: Double = ListaPedidos.sumByDouble { Lpedidos.Cantidad!!.toInt() * Lpedidos.Precio.toDouble() }
            //val precioDouble: Double = Lpedidos.Precio.toString().toDouble()
            //val cantidad: Int = Lpedidos.Cantidad!!.toInt()
            //val TotalPagar = cantidad*precioDouble
            //itemView.total_pagar.text = "$${total}"
            itemView.text_eliminar_item.setOnClickListener {
                clickPedido.onClickDeleteItem(idpedidos, Lpedidos.Imagen, Lpedidos.Nombre)
            }

        }



    }
    interface OnclickDeleteItem{
        fun onClickDeleteItem(id:String, imagenPedido:String, Nombre:String)
    }
}
