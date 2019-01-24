package com.example.usrdel.a2018movilesgr2

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Usuario(public var nombre: String,
              var edad: Int,
              var fechaNacimiento: Date,
              var sueldo: Double) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readSerializable() as Date,
            parcel.readDouble()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(edad)
        parcel.writeSerializable(fechaNacimiento)
        parcel.writeDouble(sueldo)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "${nombre}"
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}


// val adrian = Usuario("asdasd")


// protocolo ip puerto link
// http://127.0.0.1:1337/inicio
// Query Params -> Consulta
// http://127.0.0.1:1337/inicio?nombre=Adrian&cedula=1718137159
// Body/Form
// http://127.0.0.1:1337/inicio
// <form></form> / HTTP ->
// Ruta
// http://127.0.0.1:1337/cuenta/200810275/facultad/sistemas
// http://127.0.0.1:1337/cuenta/201012431/facultad/matematica
// http://127.0.0.1:1337/cuenta/201112341/facultad/fisica
// http://127.0.0.1:1337/cuenta/201112341/facultad/electrica

// http://127.0.0.1:1337/cuenta GET
// http://127.0.0.1:1337/cuenta POST
// http://127.0.0.1:1337/cuenta PUT
// http://127.0.0.1:1337/cuenta DELETE

// http://127.0.0.1:1337/usuario/crearUsuario POST
// http://127.0.0.1:1337/usuario/crear POST
// http://127.0.0.1:1337/usuario/creando POST
// http://127.0.0.1:1337/usuario/nuevo POST

// REST / RESTFUL


// http://127.0.0.1:1337/


// MODELO -> Tabla de la base

// http://127.0.0.1:1337/Estudiante
// Estudiante

// Obtener Todos -> Todos Estudiantes
// http://127.0.0.1:1337/Estudiante GET

// Crear -> Nuevo Estudiante
// http://127.0.0.1:1337/Estudiante POST
// BodyParams FormParams (Parametros)

// BuscarPorId -> 1 estudiante
// http://127.0.0.1:1337/Estudiante/1 GET


// BorrarPorId -> ¿?
// http://127.0.0.1:1337/Estudiante/1 DELETE

// -> BACKEND

// ActualizarPorId -> 1 estudiante
// http://127.0.0.1:1337/Estudiante/1 PUT
// BodyParams FormParams (Parametros)








































