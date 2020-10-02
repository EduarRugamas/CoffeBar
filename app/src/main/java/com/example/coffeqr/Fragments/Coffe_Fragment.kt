@file:Suppress("DEPRECATION")

package com.example.coffeqr.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeqr.Adapters.CoffeAdapter
import com.example.coffeqr.Class.MyViewModel
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.fragment_coffe.*
import kotlinx.android.synthetic.main.fragment_coffe.rcCoffes
import kotlinx.android.synthetic.main.fragment_coffe.view.*

@Suppress("DEPRECATION")



class Coffe_Fragment : Fragment() {

    private lateinit var adapterC: CoffeAdapter

    private val viewModel by lazy { ViewModelProviders.of(this).get(MyViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_coffe, container, false)


                rcCoffes.apply {
                    adapterC = CoffeAdapter(requireActivity())
                    view.rcCoffes.layoutManager = LinearLayoutManager(activity)
                    view.rcCoffes.adapter = adapterC
                    view.shimmer_container.visibility = view.visibility
                    view.shimmer_container.startShimmer()
                    viewModel.fetchDataCoffe().observe(requireActivity(), {
                        view.shimmer_container.stopShimmer()
                        view.shimmer_container.visibility = View.GONE
                        adapterC.setListData(it)
                        adapterC.notifyDataSetChanged()
                    })
                }


        return view
    }

}


