package com.example.coffeqr.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeqr.Class.ListDataCoffe
import com.example.coffeqr.Class.ListDataPostres
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.item_coffe.view.*
import kotlinx.android.synthetic.main.item_coffe.view.imageView
import kotlinx.android.synthetic.main.item_desserts.view.*

class PostresAdapter(private val context: Context) : RecyclerView.Adapter<PostresAdapter.PostresViewHolder>() {

    //lista vacia tipo mutablelist para setear la informacion
    private var ListPostres = mutableListOf<ListDataPostres>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostresViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_desserts, parent,false)

        return PostresViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostresViewHolder, position: Int) {
        val postres = ListPostres[position]
        holder.ListViewPostres(postres)
    }

    override fun getItemCount(): Int {
       return if (ListPostres.size > 0){
            ListPostres.size
        }else{
            0
        }
    }


    inner class PostresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun ListViewPostres(postres: ListDataPostres) {
            Glide.with(context).load(postres.imageUrl).into(itemView.imageViewP)
            itemView.nombrePostre.text = postres.nombre
            itemView.precioPostre.text = postres.precio
        }
    }

    //metodo donde seteamos la data a la mutablelist vacia
    fun setListData(data: MutableList<ListDataPostres>) {
        ListPostres = data
    }


}