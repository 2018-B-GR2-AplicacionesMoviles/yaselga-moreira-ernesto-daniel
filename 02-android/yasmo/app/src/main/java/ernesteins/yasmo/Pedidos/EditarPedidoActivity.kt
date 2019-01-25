package ernesteins.yasmo.Pedidos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ernesteins.yasmo.BDD.SqliteHelper
import ernesteins.yasmo.MainActivity
import ernesteins.yasmo.R
import kotlinx.android.synthetic.main.activity_editar_pedido.*

class EditarPedidoActivity : AppCompatActivity() {
    val helper =  SqliteHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_pedido)

        val pedido_m = intent.getParcelableExtra<Pedido?>("pedido")
        editar_pedido_editText_id.setText(pedido_m?.id_pedido)
        editar_pedido_editText_id_cliente.setText(pedido_m?.id_cliente)
        editar_pedido_editText_id_prenda.setText(pedido_m?.id_prenda)
        editar_pedido_editText_precioTotal.setText(pedido_m?.precio_total.toString())

        //CREAR
        editar_pedido_button_crear.setOnClickListener {
            AgregarPedido(
                    editar_pedido_editText_id_cliente.text.toString(),
                    editar_pedido_editText_id_prenda.text.toString(),
                    editar_pedido_editText_precioTotal.text.toString())
        }

        //EDITAR
        editar_pedido_button_editar.setOnClickListener{
            if (pedido_m != null) {
                if(helper.actualizarPedido(
                                pedido_m.id_pedido,
                                editar_pedido_editText_id_cliente.text.toString(),
                                editar_pedido_editText_id_prenda.text.toString(),
                                editar_pedido_editText_precioTotal.text.toString())
                ){
                    regresarNavegacion()
                }else{
                    Log.i("Pedido","FALLO AL ACTUALIZAR")
                }
            }
        }

        //BORRAR
        editar_pedido_button_borrar.setOnClickListener {
            if (pedido_m != null) {
                if(helper.eliminarPedido(pedido_m.id_pedido)
                ){
                    regresarNavegacion()
                }else{
                    Log.i("Pedido","FALLO AL BORRAR")
                }
            }
        }
    }

    fun AgregarPedido( descripcion:String, material:String, color:String){
        if(helper.insertarPedido(descripcion,material,color)){
            this.regresarNavegacion();
        }else{
            Log.e("Pedido","Fallo al crear")
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
