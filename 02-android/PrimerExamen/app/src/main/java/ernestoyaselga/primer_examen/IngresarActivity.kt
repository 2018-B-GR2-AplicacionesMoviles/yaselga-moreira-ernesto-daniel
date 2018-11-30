package ernestoyaselga.primer_examen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ingresar.*

class IngresarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar)
        btn_ingresar.setOnClickListener{
            Grabar()
        }
    }

    fun Grabar(){
        Casa(text_ciudad.text.toString(), text_barrio.text.toString(),text_direccion.text.toString(),text_referencia.text.toString(),text_metros.text.toString().toInt(),text_precio.text.toString().toDouble())
    }
}
