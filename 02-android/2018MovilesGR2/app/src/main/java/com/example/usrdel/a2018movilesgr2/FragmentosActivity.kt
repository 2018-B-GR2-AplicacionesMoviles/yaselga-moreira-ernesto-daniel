package com.example.usrdel.a2018movilesgr2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import kotlinx.android.synthetic.main.activity_fragmentos.*

class FragmentosActivity : AppCompatActivity() {

    lateinit var fragmentoActual: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmentos)


        fragmentoActual = PrimerFragment()

        crearFragmentoUno()

        button_crear_primer_fragmento
                .setOnClickListener {
                    crearFragmentoUno()
                }

        button_crear_segundo_fragmento
                .setOnClickListener {
                    crearFragmentoDos()
                }


        /*
        boton_tab1.setOnClickListener { view ->
            reemplazarFragmentoActual(FragmentoUno())
        }
        boton_tab2.setOnClickListener { view ->
            reemplazarFragmentoActual(FragmentoDos())
        }
        boton_tab3.setOnClickListener { view ->
            reemplazarFragmentoActual(FragmentoTres())
        }
        boton_destruir_fragmento.setOnClickListener { view ->
            destruirFragmentoActual()
        }
        */
    }

    fun crearFragmentoUno() {

        destruirFragmentoActual()


        // Manager
        val fragmentManager = supportFragmentManager

        // Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Crear instancia de fragmento
        val primerFragmento = PrimerFragment()

        val argumentos = Bundle()

        argumentos.putString("nombre", "Adrian Eguez")
        argumentos.putInt("edad", 29)

        primerFragmento.arguments = argumentos

        // Anadir fragmento

        // fragmentTransaction.remove(fragmentoActual)
        fragmentTransaction.replace(R.id.relative_layout_fragmentos, primerFragmento)

        fragmentoActual = primerFragmento


        // Commit
        fragmentTransaction.commit()
    }

    fun crearFragmentoDos() {
        destruirFragmentoActual()
        // Manager
        val fragmentManager = supportFragmentManager
        // Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()
        // Crear instancia de fragmento
        val segundoFragmento = SegundoFragment()
        val argumentos = Bundle()
        segundoFragmento.arguments = argumentos
        // Anadir fragmento
        fragmentTransaction.replace(R.id.relative_layout_fragmentos, segundoFragmento)
        fragmentoActual = segundoFragmento
        // Commit
        fragmentTransaction.commit()
    }

    fun destruirFragmentoActual() {
        val fragmentManager = supportFragmentManager
        // Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(fragmentoActual)
        fragmentTransaction.commit()
    }


    /*
    fun destruirFragmentoActual() {
        val fragmentManager = supportFragmentManager
        // Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(fragmentoActual)
        fragmentTransaction.commit()
    }


    fun reemplazarFragmentoActual(fragmento: android.support.v4.app.Fragment) {
        fragmentoActual = fragmento
        // Manager
        val fragmentManager = supportFragmentManager
        // Transacciones
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.rl_fragmento, fragmento)
        fragmentTransaction.commit()
    }
    */
}
