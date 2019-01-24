package ernesteins.yasmo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import ernesteins.yasmo.Catalogo.PrendaFragment
import ernesteins.yasmo.Clientes.ClienteFragment
import ernesteins.yasmo.Pedidos.PedidoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                crearFragmentoPrenda()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                crearFragmentocliente()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                crearFragmentoPedido()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
    lateinit var fragmentoActual: Fragment
    lateinit var contexto: MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentoActual = ClienteFragment()
        crearFragmentoPrenda()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        fragmentoActual = PrendaFragment()
        crearFragmentoPrenda()

    }
    fun destruirFragmentoActual() {
        val fragmentManager = supportFragmentManager
        // Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(fragmentoActual)
        fragmentTransaction.commit()
    }

    fun crearFragmentocliente() {

        destruirFragmentoActual()


        // Manager
        val fragmentManager = supportFragmentManager

        // Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Crear instancia de fragmento
        val primerFragmento = ClienteFragment()

        // Anadir fragmento

        // fragmentTransaction.remove(fragmentoActual)
        fragmentTransaction.replace(R.id.relativeLayout, primerFragmento)

        fragmentoActual = primerFragmento


        // Commit
        fragmentTransaction.commit()
    }

    fun crearFragmentoPrenda() {
        destruirFragmentoActual()
        // Manager
        val fragmentManager = supportFragmentManager

        // Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Crear instancia de fragmento
        val primerFragmento = PrendaFragment()

        // Anadir fragmento

        // fragmentTransaction.remove(fragmentoActual)
        fragmentTransaction.replace(R.id.relativeLayout, primerFragmento)

        fragmentoActual = primerFragmento


        // Commit
        fragmentTransaction.commit()

    }
    fun crearFragmentoPedido() {
        destruirFragmentoActual()

        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()

        val primerFragmento = PedidoFragment()
        fragmentTransaction.replace(R.id.relativeLayout, primerFragmento)
        fragmentoActual = primerFragmento
        fragmentTransaction.commit()

    }
}
