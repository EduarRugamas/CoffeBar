package com.example.coffeqr.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeqr.Adapters.CoffeAdapter
import com.example.coffeqr.Adapters.PostresAdapter
import com.example.coffeqr.Class.MyViewModel
import com.example.coffeqr.Class.ViewModelPostres
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.fragment_coffe.*
import kotlinx.android.synthetic.main.fragment_coffe.view.*
import kotlinx.android.synthetic.main.fragment_coffe.view.shimmer_container
import kotlinx.android.synthetic.main.fragment_desserts.*
import kotlinx.android.synthetic.main.fragment_desserts.view.*


class desserts_Fragment : Fragment() {
    private lateinit var adapterP: PostresAdapter
    private val viewModel by lazy { ViewModelProviders.of(this).get(ViewModelPostres::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_desserts, container, false)

        dataRecyclerview()

        return view
    }
    private fun dataRecyclerview(){
        rcDesserts.apply {
            adapterP = PostresAdapter(requireActivity())
            view!!.rcDesserts.layoutManager = LinearLayoutManager(activity)
            view!!.rcDesserts.adapter = adapterP
            view!!.shimmer_container.visibility = view!!.visibility
            view!!.shimmer_container.startShimmer()
            viewModel.fetchDataPostres().observe(requireActivity(), {
                view!!.shimmer_container.stopShimmer()
                view!!.shimmer_container.visibility = View.GONE
                adapterP.setListData(it)
                adapterP.notifyDataSetChanged()
            })
        }
    }



}