package com.example.coffeqr.Class

import android.os.Parcel
import android.os.Parcelable

data class DataListPostres (

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

    companion object CREATOR : Parcelable.Creator<DataListPostres> {
        override fun createFromParcel(parcel: Parcel): DataListPostres {
            return DataListPostres(parcel)
        }

        override fun newArray(size: Int): Array<DataListPostres?> {
            return arrayOfNulls(size)
        }
    }
}