package com.example.usrdel.a2018movilesgr2

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_respuesta.*

class IntentRespuestaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_respuesta)

        val texto: String? = intent.getStringExtra(Intent.EXTRA_TEXT)
        Log.i("intent-texto", "Texto: ${texto}")

        button_enviar_intent_respuesta
                .setOnClickListener {
                    this.enviarIntentRespuesta()
                }

        button_result_propio
                .setOnClickListener {
                    this.enviarIntentRespuestaPropio()
                }
    }

    fun enviarIntentRespuestaPropio() {
        val intentRespuestaNombreApellido = Intent(this, ResultadoPropioActivity::class.java);
        this.startActivityForResult(intentRespuestaNombreApellido, requestCodeNombreApellido)

    }

    fun enviarIntentRespuesta() {
        val intentRespuesta = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        this.startActivityForResult(intentRespuesta, requestCodeContactos);

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            requestCodeContactos -> {

                when (resultCode) {
                    RESULT_OK -> {
                        Log.i("contactos", "respuesta ${data}")
                        var cursor: Cursor? = null

                        try {

                            var numeroTelefono: String? = null
                            var nombre: String? = null
                            var tieneNumeroTelefono: String? = null
                            val uri = data?.data

                            cursor = contentResolver.query(uri, null, null, null, null)
                            cursor!!.moveToFirst()

                            val indiceTelefono = cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            val indiceNombre = cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                            val indiceTieneNumeroTelefono = cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER)

                            numeroTelefono = cursor!!.getString(indiceTelefono)
                            nombre = cursor!!.getString(indiceNombre)
                            tieneNumeroTelefono = cursor!!.getString(indiceTieneNumeroTelefono)

                            Log.i("contactos", "Telefono: ${numeroTelefono} - Nombre: ${nombre}")
                            Log.i("contactos", "Tiene numero de telefono ${tieneNumeroTelefono}")

                        } catch (e: Exception) {
                            Log.i("contactos", "Error")
                        }
                    }
                    RESULT_CANCELED -> {
                        Log.i("contactos", "No se selecciono ningun contacto")
                    }
                }


            }
            requestCodeNombreApellido -> {
                when (resultCode) {
                    RESULT_OK -> {
                        Log.i("intent-nombre-apellido", "LLEGOOOO ${data!!.getStringExtra("nombre")}")
                        Log.i("intent-nombre-apellido", "LLEGOOOO ${data!!.getStringExtra("apellido")}")
                    }
                    RESULT_CANCELED -> {
                        Log.i("contactos", "No se selecciono ningun contacto")
                    }
                }

            }
            else -> {
                Log.e("indefinido", "indefinido")
            }
        }


    }

    companion object {
        val requestCodeContactos = 101
        val requestCodeNombreApellido = 102
    }
}


















