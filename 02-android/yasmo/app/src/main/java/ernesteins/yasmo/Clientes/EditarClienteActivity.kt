package ernesteins.yasmo.Clientes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import ernesteins.yasmo.R
import ernesteins.yasmo.BDD.SqliteHelper
import ernesteins.yasmo.MainActivity
import kotlinx.android.synthetic.main.activity_editar_cliente.*

class EditarClienteActivity : AppCompatActivity() {
    val helper =  SqliteHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_cliente)

        val cliente_m = intent.getParcelableExtra<Cliente?>("cliente")
        editar_cliente_editText_id.setText(cliente_m?.id_cliente)
        editar_cliente_editText_nombre.setText(cliente_m?.nombre)
        editar_prenda_editText_material.setText(cliente_m?.telefono)

        editar_cliente_button_crear.setOnClickListener {
            AgregarCliente(editar_cliente_editText_id.text.toString(),
                    editar_cliente_editText_nombre.text.toString(),
                    editar_prenda_editText_material.text.toString())
        }

        editar_cliente_button_editar.setOnClickListener{
            if (cliente_m != null) {
                if(helper.actualizarCliente(
                                cliente_m.id_cliente,
                                editar_cliente_editText_nombre.text.toString(),
                                editar_prenda_editText_material.text.toString())
                ){
                    regresarNavegacion()
                }else{
                    Log.i("Cliente","FALLO AL ACTUALIZAR")
                }
            }
        }

        editar_cliente_button_borrar.setOnClickListener {
            if (cliente_m != null) {
                if(helper.eliminarCliente(cliente_m.id_cliente)
                ){
                    regresarNavegacion()
                }else{
                    Log.i("Cliente","FALLO AL BORRAR")
                }
            }
        }
    }

    fun AgregarCliente(id:String, nombre:String, telefono:String){
        if(helper.insertarCliente(id,nombre,telefono)){
            this.regresarNavegacion();
        }else{
            Log.e("Cliente","Fallo al crear")
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
