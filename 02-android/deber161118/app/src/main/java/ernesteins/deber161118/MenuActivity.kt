package ernesteins.deber161118

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btn_galeria.setOnClickListener {
            this.irAGaleria()
        }
        btn_nuevo_pedido.setOnClickListener{
            this.irANuevoPedido()
        }
        btn_pedidos.setOnClickListener {
            this.irAPedidos()
        }
    }
    fun irAGaleria(){
        val irAG = Intent(this, GaleriaActivity::class.java)
        this.startActivity(irAG)
    }
    fun irANuevoPedido(){
        val irAN = Intent(this, GaleriaActivity::class.java)
        this.startActivity(irAN)
    }
    fun irAPedidos(){
        val irAP = Intent(this, GaleriaActivity::class.java)
        this.startActivity(irAP)
    }
}
