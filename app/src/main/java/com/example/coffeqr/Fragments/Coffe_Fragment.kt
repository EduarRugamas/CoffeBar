@file:Suppress("DEPRECATION")

package com.example.coffeqr.Fragments


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeqr.Adapters.CoffeAdapter
import com.example.coffeqr.Class.DataListCoffe
import com.example.coffeqr.R
import com.example.coffeqr.Screens.OrdenPedidos
import kotlinx.android.synthetic.main.fragment_coffe.*
import kotlinx.android.synthetic.main.fragment_coffe.view.*
//import kotlinx.android.synthetic.main.fragment_coffe.view.btn_pedidos


class Coffe_Fragment : Fragment(){

    val cafesLista = ArrayList<DataListCoffe>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_coffe, container, false)

        Handler().postDelayed({
            shimmer_container.startShimmer()
            shimmer_container.visibility = view.visibility
            shimmer_container.stopShimmer()
            shimmer_container.visibility = View.GONE


            rcCoffes.apply {
                view.rcCoffes!!.layoutManager = LinearLayoutManager(activity)

                RdataLoad()

                val CoffeAdapter = CoffeAdapter(cafesLista)
                view.rcCoffes!!.adapter = CoffeAdapter
                view.rcCoffes!!.setHasFixedSize(true)
            }
        },2000)


        return view
    }

    private fun RdataLoad(){

        cafesLista.add(
            DataListCoffe(
            "Cafe Americano", 1.00, "Esta es la descripcion de un cafe americano","https://i.ibb.co/9Gr9kFF/americano.png"
        )
        )
        cafesLista.add(
            DataListCoffe(
            "Cafe Cappuchino", 1.50, "Esta es la descripcion de un cafe cappuchino", "https://i.ibb.co/ZVYNt6L/cappuccino.png"
        )
        )
        cafesLista.add(DataListCoffe(
            "Chocolate", 2.00, "Esta es la descripcion de un chocolate","https://i.ibb.co/W69bzFw/chocolate.png"
        ))
        cafesLista.add(DataListCoffe(
            "Cocoa", 1.50, "Esta es la descripcion de cocoa","https://i.ibb.co/DzhxzBD/cocoa.png"
        ))
        cafesLista.add(DataListCoffe(
            "Cafe Espresso", 3.50, "Esta es la descripcion de un cafe Expresso","https://i.ibb.co/grBMp4f/espresso.png"
        ))

        cafesLista.add(DataListCoffe(
            "Cafe Fraoe", 2.00, "Esta es la descripcion de un cafe frappe","https://i.ibb.co/SdHV8S9/frape.png"
        ))
        cafesLista.add(DataListCoffe(
            "Cafe Americano", 1.00, "Esta es la descripcion de un cafe americano","https://i.ibb.co/9Gr9kFF/americano.png"
        ))
    }



}


