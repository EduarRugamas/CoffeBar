package com.example.coffeqr.Class

import android.os.Parcel
import android.os.Parcelable

 class DataListCoffe (
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val imagen: String,
    var expandible: Boolean = false



){
     fun getPrice() = "$$precio"
 }


