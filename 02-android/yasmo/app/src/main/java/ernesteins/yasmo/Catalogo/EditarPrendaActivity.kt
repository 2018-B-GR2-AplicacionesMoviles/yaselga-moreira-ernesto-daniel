package ernesteins.yasmo.Catalogo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ernesteins.yasmo.BDD.SqliteHelper
import ernesteins.yasmo.MainActivity
import ernesteins.yasmo.R
import kotlinx.android.synthetic.main.activity_editar_prenda.*

class EditarPrendaActivity : AppCompatActivity() {
    val helper =  SqliteHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_prenda)

        val prenda_m = intent.getParcelableExtra<Prenda?>("prenda")
        editar_prenda_editText_id.setText(prenda_m?.id_prenda)
        editar_prenda_editText_descripcion.setText(prenda_m?.descripcion)
        editar_prenda_editText_material.setText(prenda_m?.material)
        editar_prenda_editText_color.setText(prenda_m?.color)

        //CREAR
        editar_prenda_button_crear.setOnClickListener {
            AgregarPrenda(
                    editar_prenda_editText_descripcion.text.toString(),
                    editar_prenda_editText_material.text.toString(),
                    editar_prenda_editText_material.text.toString())
        }

        //EDITAR
        editar_prenda_button_editar.setOnClickListener{
            if (prenda_m != null) {
                if(helper.actualizarPrenda(
                                prenda_m.id_prenda,
                                editar_prenda_editText_descripcion.text.toString(),
                                editar_prenda_editText_material.text.toString(),
                                editar_prenda_editText_color.text.toString())
                ){
                    regresarNavegacion()
                }else{
                    Log.i("Prenda","FALLO AL ACTUALIZAR")
                }
            }
        }

        //BORRAR
        editar_prenda_button_borrar.setOnClickListener {
            if (prenda_m != null) {
                if(helper.eliminarPrenda(prenda_m.id_prenda)
                ){
                    regresarNavegacion()
                }else{
                    Log.i("Prenda","FALLO AL BORRAR")
                }
            }
        }
    }

    fun AgregarPrenda( descripcion:String, material:String, color:String){
        if(helper.insertarPrenda(descripcion,material,color)){
            this.regresarNavegacion();
        }else{
            Log.e("Prenda","Fallo al crear")
        }
    }

    fun regresarNavegacion(){
        val intent = Intent(
                this,
                MainActivity::class.java
        )
        startActivity(intent)
    }
}
