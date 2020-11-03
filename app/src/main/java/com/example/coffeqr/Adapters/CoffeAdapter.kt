package com.example.coffeqr.Adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeqr.Class.DataListCoffe
import com.example.coffeqr.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.item_coffe.view.*

class CoffeAdapter constructor(
    private val listaCaffes: List<DataListCoffe>,

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var CantidadProductoCafes = 0
        private var nombreCafe = ""
        private var Imagen = ""
        private var precioCafe = ""
        private val dbCafes = FirebaseFirestore.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_coffe,parent,false)

        return CaffeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //holder.itemView.card_V_Coffe.animation = AnimationUtils.loadAnimation(holder.itemView.card_V_Coffe.context,R.anim.load_anim)

        val listCafes: DataListCoffe = listaCaffes[position]
        (holder as CaffeViewHolder).bind(listCafes)

            //codigo para el desplego del carrito de items cafe
            val esExpandible: Boolean = listaCaffes[position].expandible
            holder.layout_expandible.visibility = if (esExpandible) VISIBLE else View.GONE

            holder.layout_container.setOnClickListener {
                val cafes = listaCaffes[position]
                cafes.expandible = !cafes.expandible
                notifyItemChanged(position)

                holder.itemView.cantidad_product_cafes.text = "0"
                CantidadProductoCafes = 0
            }

        if (esExpandible){
            VISIBLE
            holder.itemView.image_expandido.setImageResource(R.drawable.expand_less)
        }else{
            holder.itemView.image_expandido.setImageResource(R.drawable.expand_more)
        }

        holder.itemView.boton_mas.setOnClickListener {
            holder.addCoffeProduct()

        }
        holder.itemView.boton_menos.setOnClickListener {
            holder.reduceCoffeProduct()
        }
    }

    override fun getItemCount(): Int = listaCaffes.size

    inner class CaffeViewHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView){
        val layout_container: LinearLayout = itemView.findViewById(R.id.linearContainer)
        val layout_expandible: LinearLayout = itemView.findViewById(R.id.Layout_expandible)

        fun bind(cafes: DataListCoffe){
            itemView.nombreCafe.text = cafes.nombre
            itemView.precioCafe.text = cafes.getPrice()
            itemView.tetx_descripcion.text = cafes.descripcion
            Glide.with(itemView.context).load(cafes.imagen).into(itemView.imageViewCoffe)

            itemView.boton_enviar_orden.text = "add orden"
            itemView.boton_enviar_orden.setOnClickListener {

                nombreCafe = cafes.nombre
                precioCafe = cafes.getPrice()
                Imagen = cafes.imagen

                val dataCafes = hashMapOf(
                    "Mesa" to "Mesa 1",
                    "Nombre" to nombreCafe,
                    "Cantidad" to CantidadProductoCafes,
                    "Precio" to precioCafe,
                    "Imagen" to Imagen
                )

                if (CantidadProductoCafes > 0){
                    dbCafes.collection("Pedidos").add(dataCafes)
                        .addOnCompleteListener { result ->
                            if (result.isSuccessful){
                                Toast.makeText(itemView.context, "Pedido agregado correctamente....!!!", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .addOnFailureListener { 
                            Toast.makeText(itemView.context, "No se pudo agregar el pedido...!!!", Toast.LENGTH_SHORT).show()
                            Log.d("Db ErrorCafes", it.toString())
                        }
                }else{
                    Toast.makeText(itemView.context, "No puedes Enviar 0", Toast.LENGTH_SHORT).show()
                }

            }
        }

        fun addCoffeProduct(){
            CantidadProductoCafes += 1
            itemView.cantidad_product_cafes.text = CantidadProductoCafes.toString()
        }

        fun reduceCoffeProduct(){
            if (CantidadProductoCafes > 0){
                CantidadProductoCafes -= 1
                itemView.cantidad_product_cafes.text = CantidadProductoCafes.toString()
            }
        }
    }



}