package yasmo.sastreria

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_menu_catalogo.setOnClickListener{
            this.irAcatalogo()
        }
        button_menu_clientes.setOnClickListener {
            this.irAclientes()
        }
        button_menu_pedidos.setOnClickListener {
            this.irAcpedidos()
        }
    }

    fun irAcatalogo(){
        val intent = Intent(this,Catalogo::class.java)
       startActivity(intent)
    }
    fun irAclientes(){
        val intent = Intent(this,Clientes::class.java)
        startActivity(intent)
    }
    fun irAcpedidos(){
        val intent = Intent(this,Pedidos::class.java)
        startActivity(intent)
    }
}
