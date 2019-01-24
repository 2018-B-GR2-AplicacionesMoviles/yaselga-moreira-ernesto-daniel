package ernesteins.yasmo.Catalogo

import android.os.Parcel
import android.os.Parcelable

class Prenda(val id_prenda: String, val descripcion: String, val material: String, val color: String): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_prenda)
        parcel.writeString(descripcion)
        parcel.writeString(material)
        parcel.writeString(color)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return this.descripcion
    }

    companion object CREATOR : Parcelable.Creator<Prenda> {
        override fun createFromParcel(parcel: Parcel): Prenda{
            return Prenda(parcel)
        }

        override fun newArray(size: Int): Array<Prenda?> {
            return arrayOfNulls(size)
        }
    }

}