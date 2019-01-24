package ernesteins.yasmo.Clientes

import android.os.Parcel
import android.os.Parcelable

class Cliente(val id_cliente: String, val nombre: String, val telefono: String): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_cliente)
        parcel.writeString(nombre)
        parcel.writeString(telefono)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return this.nombre
    }

    companion object CREATOR : Parcelable.Creator<Cliente> {
        override fun createFromParcel(parcel: Parcel): Cliente {
            return Cliente(parcel)
        }

        override fun newArray(size: Int): Array<Cliente?> {
            return arrayOfNulls(size)
        }
    }

}