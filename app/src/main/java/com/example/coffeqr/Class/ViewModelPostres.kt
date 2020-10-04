package com.example.coffeqr.Class

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelPostres: ViewModel() {

        val datos = RepoPostres()
    fun fetchDataPostres(): LiveData<MutableList<ListDataPostres>>{
        val dataMutable = MutableLiveData<MutableList<ListDataPostres>>()
            datos.getPostresData().observeForever { PostresList ->
                dataMutable.value = PostresList

            }
        return dataMutable
    }
}