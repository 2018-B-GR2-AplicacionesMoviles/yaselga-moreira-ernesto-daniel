package ernestoyaselga.primer_examen

import android.os.Parcel
import android.os.Parcelable

class Casa(var ciudad:String,
           var barrio:String,
           var direccion:String,
           var referencia:String,
           var precio:Int,
           var tamanio:Double):Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readDouble()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ciudad)
        parcel.writeString(barrio)
        parcel.writeString(direccion)
        parcel.writeString(referencia)
        parcel.writeInt(precio)
        parcel.writeDouble(tamanio)
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
        return "${ciudad} - ${barrio} - ${precio}"
    }

}