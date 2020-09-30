package com.example.coffeqr.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeqr.Class.ListDataCoffe
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.item_coffe.view.*

class CoffeAdapter(private val context:Context) : RecyclerView.Adapter<CoffeAdapter.CoffeViewHolder>() {

        //lista vacia tipo mutablelist para setear la informacion
    private var ListCoffe = mutableListOf<ListDataCoffe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_coffe,parent,false)
        return CoffeViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoffeViewHolder, position: Int) {
        val coffe = ListCoffe[position]
        holder.ListViewCoffe(coffe)
    }

    override fun getItemCount(): Int {
        return if (ListCoffe.size > 0){
            ListCoffe.size
        }else {
            0
        }
    }

    inner class CoffeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            fun ListViewCoffe (coffe: ListDataCoffe){
                Glide.with(context).load(coffe.imageUrl).into(itemView.imageView)
                itemView.nombreCafe.text = coffe.nombre
                itemView.precioCafe.text = coffe.precio
            }
    }

        //metodo donde seteamos la data a la mutablelist vacia
     fun setListData(data: MutableList<ListDataCoffe>){
        ListCoffe = data
    }


}