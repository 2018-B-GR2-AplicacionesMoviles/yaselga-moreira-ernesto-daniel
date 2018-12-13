package ernestoyaselga.primer_examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ingresar.*

class IngresarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar)

        button_guardar
            .setOnClickListener {
                this.devolverInsertar()
            }
    }
    fun devolverInsertar() {

        val numeroCasa = id_txt.text.toString()
        val descripcion = desc_txt.text.toString()
        val m2 = m2_txt.text.toString()
        val precio = precio_txt.text.toString()

        val intentRespuesta = Intent()

        intentRespuesta.putExtra("numeroCasa", numeroCasa)
        intentRespuesta.putExtra("descripcion", descripcion)
        intentRespuesta.putExtra("m2", m2)
        intentRespuesta.putExtra("precio", precio)

        this.setResult(
            RESULT_OK,
            intentRespuesta
        )

        this.finish()


    }
}
