package com.example.coffeqr.Class

import android.os.Parcel
import android.os.Parcelable


data class DataListCoffe (
    val nombre: String,
    val precio: Double,
    val imagen: String
) : Parcelable {

    fun getDisplayPrice() = "$$precio"
    
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeDouble(precio)
        parcel.writeString(imagen)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataListCoffe> {
        override fun createFromParcel(parcel: Parcel): DataListCoffe {
            return DataListCoffe(parcel)
        }

        override fun newArray(size: Int): Array<DataListCoffe?> {
            return arrayOfNulls(size)
        }
    }
}