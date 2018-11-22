package ernestoyaselga.a2018b_android

import android.os.Parcel
import android.os.Parcelable
import java.util.*

//transformar una clase a parcelable para poder enviarlas en un intent
class Usuario(var nombre: String,
              var edad:Int,
              var fechanavimiento:Date,
              var sueldo:Double): Parcelable { //class nombre-de-la-calse (primer constructor)
    //segundo constructor
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readSerializable() as Date,
            parcel.readSerializable() as Double) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(edad)
        parcel.writeSerializable(fechanavimiento)
        parcel.writeDouble(sueldo)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "${nombre}"
    }
}