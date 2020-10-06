package com.example.coffeqr.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeqr.Class.DataListPostres
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.item_desserts.view.*

class PostresAdapter
constructor(
    private val ListPostres: List<DataListPostres>,
    private val postreClick: onClickItemPostres
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_desserts, parent, false)
        return PostresViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostresViewHolder).bind(ListPostres[position])
    }

    override fun getItemCount(): Int = ListPostres.size

    inner class PostresViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(postres: DataListPostres) {
            itemView.nombrePostre.text = postres.nombre
            itemView.precioPostre.text = "$"+postres.precio.toString()
            Glide.with(itemView.context).load(postres.imagen).into(itemView.imageViewP)
            itemView.setOnClickListener {
                postreClick.OnClickListener(postres)
            }
        }
    }

    interface onClickItemPostres{
        fun OnClickListener(Postres: DataListPostres)
    }
}