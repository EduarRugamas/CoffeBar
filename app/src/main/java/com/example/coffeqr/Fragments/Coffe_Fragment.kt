@file:Suppress("DEPRECATION")

package com.example.coffeqr.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeqr.Adapters.CoffeAdapter
import com.example.coffeqr.Class.MyViewModel
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.fragment_coffe.*

@Suppress("DEPRECATION")



class Coffe_Fragment : Fragment() {

    lateinit var adapter: CoffeAdapter

    private val viewModel by lazy { ViewModelProviders.of(this).get(MyViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_coffe, container, false)
        //funcion de add title to toolbar
            BarTitle()

            adapter = CoffeAdapter(requireContext())

            rcCoffes.layoutManager = LinearLayoutManager(requireContext())
            rcCoffes.adapter = adapter
            Data()

        return view
    }

    private fun BarTitle(){
        barTitle.title = getString(R.string.title_coffe)
        AppCompatActivity().setSupportActionBar(barTitle)
    }

    fun Data(){

        shimmer_container.startShimmer()
        viewModel.fetchDataCoffe().observe(requireActivity(), {
            shimmer_container.hideShimmer()
            shimmer_container.stopShimmer()
            this.shimmer_container.visibility = View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }


}


