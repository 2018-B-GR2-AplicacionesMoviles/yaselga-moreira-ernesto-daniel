package ernestoyaselga.a2018b_android

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_respuesta_.*
import kotlin.math.log

class Respuesta_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val texto: String? = intent.getStringExtra(Intent.EXTRA_TEXT)
        Log.i("intent-texto", "Texto: ${texto}")

        setContentView(R.layout.activity_respuesta_)
        button_enviar_respuesta.setOnClickListener {
                    this.enviarIntentRespuesta()
                }
        boton_resultado.setOnClickListener(){
            this.enviarIntentRespuestaPropia()
        }
    }

    fun enviarIntentRespuestaPropia(){
        val intentRespuestaNombreApellido = Intent(this,activity_resultado_propio::class.java)
        this.startActivityForResult(intentRespuestaNombreApellido, ernestoyaselga.a2018b_android.Respuesta_Activity.requestCodeNombreApellido)
    }

    fun enviarIntentRespuesta(){
        val intentRespuesta = Intent(Intent.ACTION_PICK,ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        this.startActivityForResult(intentRespuesta , ernestoyaselga.a2018b_android.Respuesta_Activity.requestCodeContactos);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            requestCodeContactos -> {
                when(resultCode){
                    RESULT_OK ->{
                        Log.i("contactos","repuesta: ${data}")

                        var cursor:Cursor? = null
                        try{
                            var numero_telf: String? = null
                            var nombre: String? = null
                            var uri = data?.data
                            cursor=contentResolver.query(uri,null,null,null,null)
                            cursor!!.moveToFirst() //esos !! le dice al kotlin "ya se que esto puede ser null pero tu tranquilo yo preocupado"
                            val indiceTelefono = cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            val indiceNombre = cursor!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

                            numero_telf = cursor!!.getString(indiceTelefono)
                            nombre = cursor!!.getString(indiceNombre)

                            Log.i("contactos","Telefono: ${numero_telf} - Nombre: ${nombre}")

                        }catch(e: Exception){
                            Log.i("contactos", "No se obtuvo ")
                        }
                    }
                    Activity.RESULT_CANCELED->{
                        Log.i("contactos","No se selecciono ningun contacto")
                    }
                    else ->{
                        Log.e("indefinido", "indefinido")
                    }
                }

            }
            requestCodeNombreApellido -> {
                Log.i("intent-nombre-apellido","ha llegaoooo!! ${data!!.extras.getString("nombre")}")
            }
        }
    }
    companion object {
        val requestCodeContactos = 101
        val requestCodeNombreApellido = 102
    }
}
