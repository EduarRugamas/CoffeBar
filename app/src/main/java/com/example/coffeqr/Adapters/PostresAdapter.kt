package com.example.coffeqr.Adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeqr.Class.DataListCoffe
import com.example.coffeqr.Class.DataListPostres
import com.example.coffeqr.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.item_coffe.view.*
import kotlinx.android.synthetic.main.item_desserts.view.*

class PostresAdapter
constructor(
    private val ListPostres: List<DataListPostres>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var CantidadProductoPostres = 0
    private var nombrePostre = ""
    private var Imagen = ""
    private var precioPostre = ""
    private val dbPostres = FirebaseFirestore.getInstance()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_desserts, parent, false)
        return PostresViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //holder.itemView.C_Postres.animation = AnimationUtils.loadAnimation(holder.itemView.C_Postres.context,R.anim.load_anim)

        val listPostre: DataListPostres = ListPostres[position]
        (holder as PostresViewHolder).bind(listPostre)

        //codigo para el desplego del carrito de items cafe
        val esExpandible: Boolean = ListPostres[position].expandible
        holder.layout_expandible.visibility = if (esExpandible) View.VISIBLE else View.GONE
        holder.layout_container.setOnClickListener {
            val postre = ListPostres[position]
            postre.expandible = !postre.expandible
            notifyItemChanged(position)

            holder.itemView.cantidad_product_postres.text = "0"
            CantidadProductoPostres = 0
        }
        if (esExpandible){
            View.VISIBLE
            holder.itemView.image_expandido_postre.setImageResource(R.drawable.expand_less)
        }else{
            holder.itemView.image_expandido_postre.setImageResource(R.drawable.expand_more)
        }

        holder.itemView.boton_mas_postre.setOnClickListener {
            holder.addCoffeProduct()

        }
        holder.itemView.boton_menos_postre.setOnClickListener {
            holder.reduceCoffeProduct()
        }


    }

    override fun getItemCount(): Int = ListPostres.size

    inner class PostresViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout_container: LinearLayout = itemView.findViewById(R.id.Layout_prostres_container)
        val layout_expandible: LinearLayout = itemView.findViewById(R.id.Layout_prostres_expandible)

        fun bind(postres: DataListPostres) {
            itemView.nombrePostre.text = postres.nombre
            itemView.precioPostre.text = postres.getPrice()
            itemView.tetx_descripcion_postres.text = postres.descripcion
            Glide.with(itemView.context).load(postres.imagen).into(itemView.imageViewPostres)

            itemView.boton_enviar_orden_postre.text = "add orden"
            itemView.boton_enviar_orden_postre.setOnClickListener {
                nombrePostre = postres.nombre
                precioPostre = postres.getPrice()
                Imagen = postres.imagen

                val dataPostre = hashMapOf(
                    "Mesa" to "Mesa 1",
                    "Nombre" to nombrePostre,
                    "Cantidad" to CantidadProductoPostres,
                    "Precio" to precioPostre,
                    "Imagen" to Imagen
                )

                if (CantidadProductoPostres > 0){
                    dbPostres.collection("Pedidos")
                        .add(dataPostre)
                        .addOnCompleteListener { result ->
                            if (result.isSuccessful){
                                Toast.makeText(itemView.context, "Pedido agregado correctamente....!!!", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(itemView.context, "No se pudo agregar el pedido...!!!", Toast.LENGTH_SHORT).show()
                            Log.d("Db ErrorPostre", it.toString())
                        }
                }else{
                    Toast.makeText(itemView.context, "No puedes Enviar 0", Toast.LENGTH_SHORT).show()
                }

            }

        }

        fun addCoffeProduct(){
            CantidadProductoPostres += 1
            itemView.cantidad_product_postres.text = CantidadProductoPostres.toString()
        }
        fun reduceCoffeProduct(){
            if (CantidadProductoPostres > 0){
                CantidadProductoPostres -= 1
                itemView.cantidad_product_postres.text = CantidadProductoPostres.toString()
            }
        }

    }

}