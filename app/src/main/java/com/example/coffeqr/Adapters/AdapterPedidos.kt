package com.example.coffeqr.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeqr.Class.DataPedidos
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.item_pedidos.view.*

class AdapterPedidos constructor(val ListaPedidos: ArrayList<DataPedidos>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pedidos, parent,false)
        return PedidosViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PedidosViewHolder).bind(ListaPedidos[position])
    }

    override fun getItemCount(): Int = ListaPedidos.size


    inner class PedidosViewHolder constructor(itemView: View) :RecyclerView.ViewHolder(itemView){
            fun bind(Lpedidos: DataPedidos){
                itemView.ItemMesa.text = Lpedidos.Mesa
                itemView.nombreItem.text = Lpedidos.Nombre
                itemView.precioItem.text = Lpedidos.Precio.toString()
                itemView.ItemCantidad.text = Lpedidos.Cantidad.toString()
                Glide.with(itemView.context).load(Lpedidos.Imagen).into(itemView.ItemImagen)
            }
    }


}