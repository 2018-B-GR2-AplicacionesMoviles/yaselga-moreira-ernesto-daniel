package ernestoyaselga.primer_examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_editar.*

class EditarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        val casa = intent.getParcelableExtra<Casa?>("casa")
        val pos = intent.getIntExtra("pos", 0)

        Log.i("intent-nombre-apellido", "casa actualizar ${casa.toString()}")

        button_actualizar.setOnClickListener {
            this.ActualizarCasa(id_editar.text.toString(), desc_editar.text.toString(), m2_editar.text.toString(), precio_editar.text.toString(), pos)
            Log.i("intent-nombre-apellido", "id editar ${id_editar.text.toString()}")

            this.irAListar()
        }
    }

    fun ActualizarCasa (indiceCasa: String, descipcionIng:String, m2Ing: String, precioIng:String, pos:Int):Unit{

        val nuevaCasa: Casa = Casa(indiceCasa,descipcionIng, m2Ing, precioIng)

        BDD.Casa[pos] = nuevaCasa

        Log.i("intent-nombre-apellido", "CASA ${BDD.Casa[pos].toString()}")
    }
    fun irAListar(){
        val intentListar = Intent(this, ConsutarActivity::class.java);
        this.startActivity(intentListar)
    }
    fun mostrarCampos(casa: Casa){
        id_editar.setText(casa.numeroCasa)
    }

}
