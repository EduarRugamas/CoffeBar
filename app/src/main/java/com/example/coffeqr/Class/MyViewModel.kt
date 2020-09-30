package com.example.coffeqr.Class

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

        val datos = Repo()
    fun fetchDataCoffe(): LiveData<MutableList<ListDataCoffe>>{
        val dataMutable = MutableLiveData<MutableList<ListDataCoffe>>()
            datos.getCoffeData().observeForever { CoffeList ->
                dataMutable.value = CoffeList

            }
        return dataMutable
    }
}