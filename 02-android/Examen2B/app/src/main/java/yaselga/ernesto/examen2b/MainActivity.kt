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

        boton_listar.setOnClickListener {
            val direccion = "http://$ip:8000/sistemas/api/"
            Log.i("http",direccion)
            cargarDatosSO(direccion,::irActividadListarOS)
        }

        boton_crear_padre.setOnClickListener{
            irActividadCrearOS()
        }


    }

    fun irActividadCrearOS(){
        val intent = Intent(
                this,
                CrearPadre::class.java
        )
        startActivity(intent)
    }

    fun irActividadListarOS(){
        finish()
        // 1 Cargar datos de la API
        // 2 Ir al Intent
        // 3 Hacer esa cosa de matar al stack
        val intent = Intent(
                this,
                ListarPadresActivity::class.java
        )
        startActivity(intent)

    }
}
