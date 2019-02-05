package yaselga.ernesto.examen2b.Paciente

import android.os.Parcel
import android.os.Parcelable

class Paciente(val nombre: String, val apellido:String, val fechaNacimiento:String, val hijos:Int, val seguro:Int,val pacienteId:Int):Parcelable{
    constructor(parcel:Parcel):this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(fechaNacimiento)
        parcel.writeInt(hijos)
        parcel.writeInt(seguro)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Paciente> {
        override fun createFromParcel(parcel: Parcel): Paciente {
            return Paciente(parcel)
        }

        override fun newArray(size: Int): Array<Paciente?> {
            return arrayOfNulls(size)
        }
    }
}