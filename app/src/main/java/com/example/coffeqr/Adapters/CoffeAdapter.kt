package com.example.coffeqr.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeqr.Class.DataListCoffe
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.item_coffe.view.*

class CoffeAdapter constructor(
    private val listaCaffes: List<DataListCoffe>,
    private val coffeClick: onClickItemCoffe,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coffe,parent,false)

        return CaffeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CaffeViewHolder).bind(listaCaffes[position])
    }

    override fun getItemCount(): Int = listaCaffes.size

    inner class CaffeViewHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bind(cafes: DataListCoffe){
            itemView.nombreCafe.text = cafes.nombre
            itemView.precioCafe.text = "$"+cafes.precio.toString()
            Glide.with(itemView.context).load(cafes.imagen).into(itemView.imageViewCoffe)
            itemView.setOnClickListener {
                coffeClick.onClick(cafes)
            }
        }
    }

    interface onClickItemCoffe {
        fun onClick(Cafes : DataListCoffe)
    }

}