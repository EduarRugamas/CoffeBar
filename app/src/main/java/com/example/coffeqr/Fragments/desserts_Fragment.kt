package com.example.coffeqr.Fragments


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeqr.Adapters.PostresAdapter
import com.example.coffeqr.Class.DataListPostres
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.fragment_desserts.*
import kotlinx.android.synthetic.main.fragment_desserts.view.*



@Suppress("DEPRECATION")
class desserts_Fragment : Fragment() {

    val postreLista = ArrayList<DataListPostres>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_desserts, container, false)

            Handler().postDelayed({
                shimmer_container.startShimmer()
                shimmer_container.visibility = view.visibility
                shimmer_container.stopShimmer()
                shimmer_container.visibility = View.GONE



                rcDesserts.apply {
                    view.rcDesserts.layoutManager = LinearLayoutManager(activity)
                    RdataLoadPostre()

                    val PostresAdapter = PostresAdapter(postreLista)
                    view.rcDesserts.adapter = PostresAdapter
                }
            },5000)



        return view
    }

    private fun RdataLoadPostre(){

        postreLista.add(
            DataListPostres(
                "Tiramisu", 1.50, "Esta es la descripcion de un tiramisu","https://i.ibb.co/dBWW9dC/tiramisu.png"
            )
        )
        postreLista.add(
            DataListPostres(
                "Blinis", 1.00, "Esta es la descripcion de un Blinis", "https://i.ibb.co/jTDwyjt/blinis.png"
            )
        )
        postreLista.add(DataListPostres(
            "Katen", 2.00, "Esta es la descripcion de un Katen","https://i.ibb.co/wWyBfyz/kante.png"
        ))
        postreLista.add(DataListPostres(
            "Panna Cotta", 2.00, "Esta es la descripcion de Panna Cotta","https://i.ibb.co/9qW4f9Z/pannakota.png"
        ))
        postreLista.add(DataListPostres(
            "Galletas", 1.00, "Esta es la descripcion de Galletas","https://i.ibb.co/mtTn1KX/galletas.png"
        ))

        postreLista.add(DataListPostres(
            "Basbousa", 1.50, "Esta es la descripcion de un Basbousa","https://i.ibb.co/ChN4GrR/bashousa.png"
        ))
        postreLista.add(
            DataListPostres(
                "Blinis", 1.00, "Esta es la descripcion de un Blinis", "https://i.ibb.co/jTDwyjt/blinis.png"
            )
        )
        postreLista.add(DataListPostres(
            "Panna Cotta", 2.00, "Esta es la descripcion de Panna Cotta","https://i.ibb.co/9qW4f9Z/pannakota.png"
        ))

    }





}