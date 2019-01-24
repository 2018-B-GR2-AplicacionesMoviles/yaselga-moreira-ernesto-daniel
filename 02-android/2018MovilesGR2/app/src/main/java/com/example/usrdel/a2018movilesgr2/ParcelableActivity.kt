package com.example.usrdel.a2018movilesgr2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ParcelableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable)

        val usuario = intent.getParcelableExtra<Usuario?>("usuario")
        val mascota = intent.getParcelableExtra<Mascota?>("mascota")
        if (usuario != null) {
            Log.i("parcelable", "Usuario ${usuario.nombre}")
        }

        Log.i("parcelable", "Mascota ${mascota!!.nombre} ${mascota!!.usuario.nombre}")

        // Proceso 1  -> val nombre = "Adrian"

        // nombre -> Serializan 10101010101010101
        // 10101010101010101 -> Deserializan nombre

        // Proceso 2  -> val apellido = "Eguez"


    }
}
