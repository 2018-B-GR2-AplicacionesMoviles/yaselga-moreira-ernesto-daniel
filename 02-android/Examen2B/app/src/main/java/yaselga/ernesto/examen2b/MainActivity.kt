package yaselga.ernesto.examen2b

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import yaselga.ernesto.examen2b.BDD.Companion.ip

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_listar_padres.setOnClickListener {
            val direccion = "http://$ip:80/sistemas/api/"
            Log.i("http",direccion)
            cargarDatosPadre(direccion,::irActividadListarPadre)
        }

        button_ingresar_padre.setOnClickListener{
            irActividadCrearPadre()
        }

    }

    fun irActividadCrearPadre(){
        val intent = Intent(
                this,
                CrearPadre::class.java
        )
        startActivity(intent)
    }
    fun irActividadListarPadre(){
        val intent = Intent(
                this,
                ListarPadresActivity::class.java
        )
        startActivity(intent)
    }


}
