package com.example.coffeqr.Class

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class Repo {

    fun getCoffeData(): LiveData<MutableList<ListDataCoffe>>{
        val listaMutable = MutableLiveData<MutableList<ListDataCoffe>>()

        FirebaseFirestore.getInstance().collection("Cafes").get().addOnSuccessListener {result ->
            val listaVacia = mutableListOf<ListDataCoffe>()
            for (dato in result){
                val imageUrl = dato.getString("imageUrl")
                val nombre = dato.getString("nombre")
                val precio = dato.getString("precio")

                val llenandoLista = ListDataCoffe(imageUrl!!,nombre!!,precio!!)
                listaVacia.add(llenandoLista)
                Log.d("variables", "$nombre, $precio")
            }
            listaMutable.value = listaVacia
        }

        return listaMutable
    }
}