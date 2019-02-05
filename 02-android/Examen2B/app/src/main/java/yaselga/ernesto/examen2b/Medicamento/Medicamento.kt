package yaselga.ernesto.examen2b.Medicamento

import android.os.Parcel
import android.os.Parcelable

class Medicamento(val nombre: String, val gramosAingerir:Double, val composicion:String, val usadoPara:String, val fechaCaducidad:String, val numeroPastillas:Int, val pacienteID:Int):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt()
            )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeDouble(gramosAingerir)
        parcel.writeString(composicion)
        parcel.writeString(usadoPara)
        parcel.writeString(fechaCaducidad)
        parcel.writeInt(numeroPastillas)
        parcel.writeInt(pacienteID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Medicamento> {
        override fun createFromParcel(parcel: Parcel): Medicamento {
            return Medicamento(parcel)
        }

        override fun newArray(size: Int): Array<Medicamento?> {
            return arrayOfNulls(size)
        }
    }
}