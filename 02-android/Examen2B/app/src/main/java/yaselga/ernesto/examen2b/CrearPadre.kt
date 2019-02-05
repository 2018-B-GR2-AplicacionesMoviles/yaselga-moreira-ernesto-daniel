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
        val pacienteS = intent.getParcelableExtra<Paciente?>("sistema")
        var esnuevo = true

        if (pacienteS != null) {
            txt_id_padre.setText(pacienteS.pacienteId.toString())
            txt_nombre.setText(pacienteS.nombre)
            txt_apellido.setText(pacienteS.apellido)
            txt_fecha.setText(pacienteS.fechaNacimiento)
            txt_medicamentos.setText(pacienteS.hijos)
            esnuevo = false
        }



        boton_registrar_so.setOnClickListener {
            if(esnuevo){
                crearActualizarSO(true)
            }else{
                crearActualizarSO(false)
            }
        }

        boton_cancelar_reg_so.setOnClickListener {
            irInicio()
        }
    }

    fun crearActualizarSO(es_nuevo:Boolean){

        val id = txt_id_padre.text.toString()
        val nombre = txt_nombre.text.toString()
        val apellido = txt_apellido.text.toString()
        val fecha = txt_fecha.text.toString()
        val hijos = txt_medicamentos.text.toString().toInt()
        val pacienteSO:Paciente
        if(es_nuevo){
            pacienteSO = Paciente(pacienteId =null, nombre = nombre, apellido = apellido, fechaNacimiento = fecha, hijos=  hijos)
        }else{
            pacienteSO = Paciente(pacienteId= id.toInt(), nombre = nombre, apellido = apellido, fechaNacimiento = fecha, hijos=  hijos)
        }

        //Crear objeto
        val parametros = listOf("nombre" to pacienteSO.nombre, "apellido" to pacienteSO.apellido,
                "fechaNacimiento" to pacienteSO.fechaNacimiento, "hijos" to pacienteSO.hijos)
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
                                cargarDatosPadre(direccion, ::irlistarSo)
                            }
                        }
                    }
        }else{
            direccion = "http://$ip:8000/sistemas/api/${pacienteSO.pacienteId}/update"
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
                                cargarDatosPadre(redire, ::irlistarSo)
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
