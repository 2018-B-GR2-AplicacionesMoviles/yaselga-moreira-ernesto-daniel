package ernesteins.yasmo.Pedidos

import android.os.Parcel
import android.os.Parcelable

class Pedido(val id_pedido: String, val id_cliente: String, val id_prenda: String, val precio_total: Int): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id_prenda)
        parcel.writeString(id_cliente)
        parcel.writeString(id_pedido)
        parcel.writeInt(precio_total)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return this.precio_total.toString()
    }

    companion object CREATOR : Parcelable.Creator<Pedido> {
        override fun createFromParcel(parcel: Parcel): Pedido{
            return createFromParcel(parcel)
        }

        override fun newArray(size: Int): Array<Pedido?> {
            return arrayOfNulls(size)
        }
    }

}