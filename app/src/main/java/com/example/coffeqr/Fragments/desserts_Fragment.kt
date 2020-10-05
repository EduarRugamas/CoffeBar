package com.example.coffeqr.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeqr.R
import kotlinx.android.synthetic.main.fragment_coffe.view.shimmer_container
import kotlinx.android.synthetic.main.fragment_desserts.*
import kotlinx.android.synthetic.main.fragment_desserts.view.*


class desserts_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_desserts, container, false)



        return view
    }






}