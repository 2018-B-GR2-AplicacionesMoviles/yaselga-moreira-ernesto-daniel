package ernestoyaselga.a2018b_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_button.*


class ButtonActivity : AppCompatActivity() {

    var nombre:String = ""
    var apellido:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        this.capturardatosdelIntent()

        actv_but_nombre_apellido.text=nombre+" "+apellido

        boton1enviar.setOnClickListener{
            enviarcorreo()
        }

    }

    fun capturardatosdelIntent(){
        this.nombre=intent.getStringExtra("nombre")
        this.apellido=intent.getStringExtra("apellido")
    }

    fun enviarcorreo(){
        val correo = txt1.text
        val subjet = txt2.text
        val texto = txt3.text

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/html"
        intent.putExtra(Intent.EXTRA_EMAIL,arrayOf(correo,"e@e.com"))
        intent.putExtra(Intent.EXTRA_EMAIL,subjet)
        intent.putExtra(Intent.EXTRA_TEXT,texto)

        startActivity(intent)
    }
}
