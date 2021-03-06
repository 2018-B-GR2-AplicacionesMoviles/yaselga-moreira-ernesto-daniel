package yaselga.ernesto.examen2b

import android.app.Activity
import android.content.DialogInterface
import android.graphics.Color
import android.support.v7.app.AlertDialog
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.beust.klaxon.Klaxon
//import yaselga.ernesto.examen2b.BDD.Companion.Pacientes
//import yaselga.ernesto.examen2b.BDD.Companion.Medicamentos
import yaselga.ernesto.examen2b.BDD.Companion.Medicamentos
import yaselga.ernesto.examen2b.BDD.Companion.Pacientes


fun mensaje(actividad:Activity,tipo: String,contenido:String){


    com.tapadoo.alerter.Alerter.create(actividad)
            .setTitle(tipo)
            .setText(contenido)
            .show()
}


fun mensaje_dialogo(actividad:Activity,contenido:String,funcion: () -> Unit){
    val builder = AlertDialog.Builder(actividad)

    builder
            .setMessage(contenido)
            .setPositiveButton(
                    "Si",
                    DialogInterface.OnClickListener { dialog, which ->
                        funcion()
                    }
            )
            .setNegativeButton(
                    "No",null
            )


    val dialogo = builder.create()
    dialogo.show()
    dialogo.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE)
    dialogo.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE)
}

fun cargarDatosSO(url:String,funcion_intent: () -> Unit){
    url.httpGet().responseString{request, response, result ->
        when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                Log.i("http", ex.toString())
            }
            is Result.Success -> {
                val data = result.get()
                Pacientes.clear()
                val wordDict = Klaxon().parseArray<Paciente>(data)
                Log.i("http", "Datos: ${wordDict.toString()}")
                if (wordDict != null) {
                    for ( item in wordDict.iterator()){
                        Pacientes.add(item)
                    }
                }

                funcion_intent()

            }
        }
    }



}

fun cargarDatosApp(url:String,funcion_intent: () -> Unit){
    url.httpGet().responseString{request, response, result ->
        when (result) {
            is Result.Failure -> {
                val ex = result.getException()
                Log.i("http", ex.toString())
            }
            is Result.Success -> {
                val data = result.get()
                Medicamentos.clear()
                val wordDict = Klaxon().parseArray<Medicamento>(data)
                Log.i("http", "Datos: ${wordDict.toString()}")
                if (wordDict != null) {
                    for ( item in wordDict.iterator()){
                        Medicamentos.add(item)
                    }
                }

                funcion_intent()

            }
        }
    }
}
