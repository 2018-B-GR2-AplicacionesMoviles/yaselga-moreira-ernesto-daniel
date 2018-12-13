package ernestoyaselga.primer_examen

import android.os.Parcel
import android.os.Parcelable


class Casa (public var numeroCasa: String, public var descripcion:String, public var m2:String, public var precio:String): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(numeroCasa)
        parcel.writeString(descripcion)
        parcel.writeString(m2)
        parcel.writeString(precio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Casa> {
        override fun createFromParcel(parcel: Parcel): Casa {
            return Casa(parcel)
        }

        override fun newArray(size: Int): Array<Casa?> {
            return arrayOfNulls(size)
        }
    }
    override fun toString(): String {
        return "Casa#${numeroCasa}. Desc: ${descripcion} Metros cuadrados: $m2  Precio: $precio"
    }




}