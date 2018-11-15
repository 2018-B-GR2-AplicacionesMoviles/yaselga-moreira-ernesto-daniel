package ernestoyaselga.a2018b_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_resultado_propio.*

class activity_resultado_propio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_propio)
        boton_devolver_respuesta.setOnClickListener(){
            this.devolverRespuesta()
        }
    }

    fun devolverRespuesta(){
        val nombre = texto_nombre.text
        val apellido = texto_apellido.text
        val intentrespuesta = Intent()
        intentrespuesta.putExtra( "nombre",nombre)
        intentrespuesta.putExtra( "apellido",apellido)
        this.setResult(android.app.Activity.RESULT_OK,intentrespuesta)
        this.finish()
    }

}
