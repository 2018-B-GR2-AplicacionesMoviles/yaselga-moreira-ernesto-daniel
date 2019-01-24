package com.example.usrdel.a2018movilesgr2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_vida.*

class CicloVidaActivity : AppCompatActivity() {

    var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        Log.i("ciclo-vida", "On Create")


        button_contador
                .setOnClickListener {
                    // algo  -> Aumentar contador
                    this.aumentarContador(1)
                }

    }

    fun revisarSiTieneUnEstadoGuardado(savedInstanceState: Bundle) {
        val contadorGuardado = savedInstanceState
                .get(SAVE_INSTANCE_STATE_CONTADOR) as Int?

        if (contadorGuardado != null) {
            contador = contadorGuardado
            this.aumentarContador(0)
        }
    }

    fun aumentarContador(suma: Int) {
        contador = contador + suma
        text_view_contador.text = contador.toString()
    }


    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "On Start")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida", "On Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida", "On Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida", "On Stop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida", "On Restart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida", "On Destroy")
    }

    override fun onSaveInstanceState(
            outState: Bundle?) {
        Log.i("ciclo-vida", "On onSaveInstanceState")

        outState?.run {
            putInt(SAVE_INSTANCE_STATE_CONTADOR, contador)
        }

        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        this.revisarSiTieneUnEstadoGuardado(savedInstanceState)

    }

    companion object {
        val SAVE_INSTANCE_STATE_CONTADOR = "contador"
    }


}
