package yaselga.ernesto.examen2b

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_padre.*
import yaselga.ernesto.examen2b.BDD.Companion.ip

class CrearPadre : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_padre)
        val sistema = intent.getParcelableExtra<PacienteSe?>("sistema")
        //val sistema: Paciente? = null
        var esnuevo = true

        if (sistema != null) {
            txt_id_paciente.setText(sistema.id.toString())
            txt_nombre.setText(sistema.nombre)
            txt_apellido.setText(sistema.apellido)
            txt_fecha.setText(sistema.fechaNacimiento)
            txt_hijos.setText(sistema.hijos.toString())
            esnuevo = false
        }



        boton_registrar_paciente.setOnClickListener {
            if(esnuevo){
                crearActualizarSO(true)
            }else{
                crearActualizarSO(false)
            }
        }

        boton_cancelar_reg_paciente.setOnClickListener {
            irInicio()
        }
    }

    fun crearActualizarSO(es_nuevo:Boolean){

        val id = txt_id_paciente.text.toString()
        val nombre = txt_nombre.text.toString()
        val version = txt_apellido.text.toString()
        val fecha = txt_fecha.text.toString()
        val peso = txt_hijos.text.toString()
        val so:Paciente
        if(es_nuevo){
            so = Paciente(id=null,nombre = nombre,apellido =  version,fechaNacimiento =  fecha,hijos =  peso.toInt())
        }else{
            so = Paciente(id=id.toInt(),nombre = nombre,apellido =  version,fechaNacimiento =  fecha,hijos =  peso.toInt())
        }

        //Crear objeto
        val parametros = listOf("nombre" to so.nombre, "apellido" to so.apellido,
                "fechaNacimiento" to so.fechaNacimiento, "hijos" to so.hijos)
        Log.i("htpp",parametros.toString())
        var direccion = ""
        if(es_nuevo){
            direccion = "http://$ip:8000/sistemas/api/"
            val url = direccion
                    .httpPost(parametros)
                    .responseString { request, response, result ->
                        when (result) {
                            is Result.Failure -> {
                                val ex = result.getException()
                                Log.i("http-p", ex.toString())
                                mensaje(this,"error","Datos no validos")

                            }
                            is Result.Success -> run {
                                val data = result.get()
                                Log.i("http-p", data)
                                mensaje(this,"Aceptado","Datos validos, espere...")
                                cargarDatosSO(direccion, ::irlistarSo)
                            }
                        }
                    }
        }else{
            direccion = "http://$ip:8000/sistemas/api/${so.id}/update"
            val url = direccion
                    .httpPut(parametros)
                    .responseString { request, response, result ->
                        when (result) {
                            is Result.Failure -> {
                                val ex = result.getException()
                                Log.i("http-p", ex.toString())
                                mensaje(this,"error","Datos no validos")

                            }
                            is Result.Success -> run {
                                val data = result.get()
                                Log.i("http-p", data)
                                mensaje(this,"Aceptado","Datos validos, espere...")
                                val redire = "http://$ip:8000/sistemas/api/"
                                cargarDatosSO(redire, ::irlistarSo)
                            }
                        }
                    }
        }
        Log.i("http",direccion)

    }

    fun irlistarSo(){
        val intent = Intent(
                this,
                ListarPadresActivity::class.java
        )
        startActivity(intent)
    }

    fun irInicio(){
        finish()
        val intent = Intent(
                this,
                MainActivity::class.java
        )
        startActivity(intent)
    }

}
