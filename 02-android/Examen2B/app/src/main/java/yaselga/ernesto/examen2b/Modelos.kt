package yaselga.ernesto.examen2b


import android.os.Parcel
import android.os.Parcelable

class Medicamento(val IDMed:Int?,
                  val nombre: String,
                  val composicion:String,
                  val fechaCaducidad:String,
                  val gramosAingerir:String,
                  val numeroPastillas:Int,
                  val usadoPara:String,
                  var codigo_barras:String,
                  val paciente:Int
                  ):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(IDMed)
        parcel.writeString(nombre)
        parcel.writeString(composicion)
        parcel.writeString(fechaCaducidad)
        parcel.writeString(gramosAingerir)
        parcel.writeInt(numeroPastillas)
        parcel.writeString(usadoPara)
        parcel.writeString(codigo_barras)
        parcel.writeInt(paciente)


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


class Paciente(val pacienteId:Int?,
               val nombre: String,
               val apellido:String,
               val fechaNacimiento:String,
               val hijos:Int):Parcelable{
    constructor(parcel:Parcel):this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(pacienteId)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(fechaNacimiento)
        parcel.writeInt(hijos)
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