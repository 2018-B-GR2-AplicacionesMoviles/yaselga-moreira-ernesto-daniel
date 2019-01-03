package ernestoyaselga.a2018b_android


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fragmentos.*
import android.support.v4.app.Fragment

class Fragmentos : AppCompatActivity() {

    lateinit var fragmentoActual: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmentos)
        //fragmentoActual = PrimerFragment()


        btn_frag_1
                .setOnClickListener {
                    crearFragmentoUno()
                }
    }

    fun crearFragmentoUno() {
        // Manager
        val fragmentManager = supportFragmentManager

        // Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Crear instancia de fragmento
        val primerFragmento = fragmento1()

        val argumentos = Bundle()

        argumentos.putString("nombre", "Ernesto Yaselga")
        argumentos.putInt("edad", 29)

        primerFragmento.arguments = argumentos

        // Anadir fragmento

        fragmentTransaction.replace(R.id.relativeLayout_fragmentos, primerFragmento)

        fragmentoActual = primerFragmento



        // Commit
        fragmentTransaction.commit()
    }
}
