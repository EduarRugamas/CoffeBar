package com.example.coffeqr.Fragments

import android.content.Intent
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
import com.example.coffeqr.Screens.ItemPostresDetails
import com.example.coffeqr.Screens.ItemPostresDetails.Companion.POSTRE_KEY
import kotlinx.android.synthetic.main.fragment_desserts.*
import kotlinx.android.synthetic.main.fragment_desserts.view.*



class desserts_Fragment : Fragment(), PostresAdapter.onClickItemPostres {

    private val postre1 = DataListPostres("Tiramisu",1.50, "https://i.ibb.co/dBWW9dC/tiramisu.png")
    private val postre2 = DataListPostres("Blinis",1.00,"https://i.ibb.co/jTDwyjt/blinis.png")
    private val postre3 = DataListPostres("Katen",2.00,"https://i.ibb.co/wWyBfyz/kante.png")
    private val postre4 = DataListPostres("Panna Cotta",2.00,"https://i.ibb.co/9qW4f9Z/pannakota.png")
    private val postre5 = DataListPostres("Galletas",1.00,"https://i.ibb.co/mtTn1KX/galletas.png")
    private val postre6 = DataListPostres("Basbousa",1.50,"https://i.ibb.co/ChN4GrR/bashousa.png")


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
                    view.rcDesserts.adapter = PostresAdapter(getDataPostres(), this@desserts_Fragment)
                }

            },5000)




        return view
    }
    private fun getDataPostres(): List<DataListPostres> {
        return listOf(
            postre1,
            postre2,
            postre3,
            postre4,
            postre5,
            postre6

        )
    }

    override fun OnClickListener(Postres: DataListPostres) {
        val intent = Intent(requireActivity(), ItemPostresDetails::class.java)
        intent.putExtra(POSTRE_KEY, Postres)
        startActivity(intent)
    }


}