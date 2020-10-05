@file:Suppress("DEPRECATION")

package com.example.coffeqr.Fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeqr.Adapters.CoffeAdapter
import com.example.coffeqr.Class.DataListCoffe
import com.example.coffeqr.R
import com.example.coffeqr.Screens.CoffeActivityDetails
import com.example.coffeqr.Screens.CoffeActivityDetails.Companion.COFFE_KEY
import kotlinx.android.synthetic.main.fragment_coffe.rcCoffes
import kotlinx.android.synthetic.main.fragment_coffe.view.*


@Suppress("DEPRECATION")


class Coffe_Fragment : Fragment(), CoffeAdapter.onClickItemCoffe {

    private val cafe1 =
        DataListCoffe("Cafe Americano", 1.00, "https://i.ibb.co/9Gr9kFF/americano.png")
    private val cafe2 =
        DataListCoffe("Cafe Cappuchino", 1.50, "https://i.ibb.co/ZVYNt6L/cappuccino.png")
    private val cafe3 =
        DataListCoffe("Chocolate", 2.00, "https://i.ibb.co/W69bzFw/chocolate.png")
    private val cafe4 =
        DataListCoffe("Cocoa", 1.50, "https://i.ibb.co/DzhxzBD/cocoa.png")
    private val cafe5 =
        DataListCoffe("Cafe Espresso", 3.50, "https://i.ibb.co/grBMp4f/espresso.png")
    private val cafe6 =
        DataListCoffe("Cafe Fraoe", 2.00, "https://i.ibb.co/SdHV8S9/frape.png")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_coffe, container, false)

        rcCoffes.apply {
            view.rcCoffes!!.layoutManager = LinearLayoutManager(activity)
            view.rcCoffes!!.adapter = CoffeAdapter(getDataCoffe(), this@Coffe_Fragment )
        }


        return view
    }




    private fun getDataCoffe(): List<DataListCoffe> {
        return listOf(
            cafe1,
            cafe2,
            cafe3,
            cafe4,
            cafe5,
            cafe6

        )
    }

    override fun onClick(Cafes: DataListCoffe) {
        val intent = Intent(requireActivity(), CoffeActivityDetails::class.java)
        intent.putExtra(COFFE_KEY, Cafes)
        startActivity(intent)
    }


}


