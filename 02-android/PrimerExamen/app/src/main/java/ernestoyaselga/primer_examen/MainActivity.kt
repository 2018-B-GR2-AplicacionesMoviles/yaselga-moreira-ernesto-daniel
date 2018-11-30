package ernestoyaselga.primer_examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_nuevo.setOnClickListener {
            IrIngresar()
        }

        btn_consultar.setOnClickListener {
            IrConsultar()
        }
    }

    fun IrIngresar(){
        val intentActiv = Intent(this,IngresarActivity::class.java)
        startActivity(intentActiv)
    }

    fun IrConsultar(){
        val intentActiv = Intent(this,ConsutarActivity::class.java)
        startActivity(intentActiv)
    }
}
