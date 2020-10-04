package com.example.coffeqr.Class

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class RepoPostres {

    fun getPostresData(): LiveData<MutableList<ListDataPostres>>{
        val listaMutable = MutableLiveData<MutableList<ListDataPostres>>()

        FirebaseFirestore.getInstance().collection("Postres").get().addOnSuccessListener {result ->
            val listaVacia = mutableListOf<ListDataPostres>()
            for (dato in result){
                val imageUrl = dato.getString("imageUrl")
                val nombre = dato.getString("nombre")
                val precio = dato.getString("precio")

                val llenandoLista = ListDataPostres(imageUrl!!,nombre!!,precio!!)
                listaVacia.add(llenandoLista)
                Log.d("variables", "$nombre, $precio")
            }
            listaMutable.value = listaVacia
        }

        return listaMutable
    }
}