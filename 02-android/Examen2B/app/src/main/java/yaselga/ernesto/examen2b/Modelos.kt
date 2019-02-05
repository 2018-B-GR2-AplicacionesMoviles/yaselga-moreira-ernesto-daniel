package yaselga.ernesto.examen2b


import android.os.Parcel
import android.os.Parcelable

class Paciente(
        var id:Int?,
        var nombre: String,
        var apellido: String,
        var fechaNacimiento: String,
        var hijos: Int){}


class Medicamento(
        var id:Int?,
        var nombre: String,
        var composicion: String,
        var fechaCaducidad: String,
        var gramosAingerir: String,
        var numeroPastillas:Int,
        var usadoPara:String,
        var codigo_barras:String,
        var paciente: Int?
){}




// PARA SERIALIZAR ENTRE INTENTS
class PacienteSe(var id:Int?,
                 var nombre: String,
                 var apellido: String,
                 var fechaNacimiento: String,
                 var hijos: Int):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(fechaNacimiento)
        parcel.writeInt(hijos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PacienteSe> {
        override fun createFromParcel(parcel: Parcel): PacienteSe {
            return PacienteSe(parcel)
        }

        override fun newArray(size: Int): Array<PacienteSe?> {
            return arrayOfNulls(size)
        }
    }
}

class MedicamentoSe(
        var id:Int?,
        var nombre: String,
        var composicion: String,
        var fechaCaducidad: String,
        var gramosAingerir: String,
        var numeroPastillas:Int,
        var usadoPara:String,
        var codigo_barras:String,
        var paciente: Int
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
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
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

    companion object CREATOR : Parcelable.Creator<MedicamentoSe> {
        override fun createFromParcel(parcel: Parcel): MedicamentoSe {
            return MedicamentoSe(parcel)
        }

        override fun newArray(size: Int): Array<MedicamentoSe?> {
            return arrayOfNulls(size)
        }
    }
}