package ernestoyaselga.a2018b_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclo_de_vida.*
import kotlinx.android.synthetic.main.activity_main.*

class CicloDeVidaActivity : AppCompatActivity() {

    var Numbercontar= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_de_vida)
        Log.i("ciclo-vida","On Create")


        btn_aumentar.setOnClickListener{
            this.contar(1)
        }
    }
    override fun onStart(){
        super.onStart()
        Log.i("ciclo-vida", "On Start")
    }

    override fun onResume(){
        super.onResume()
        Log.i("ciclo-vida","On Resume")
    }

    override fun onPause(){
        super.onPause()
        Log.i("ciclo-vida","On Pause")
    }

    override fun onStop(){
        super.onStop()
        Log.i("ciclo-vida","On Stop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida","On Restart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida","On Destroy")
    }

    fun contar(anterior:Int){
        Numbercontar = Numbercontar + anterior
        lb_numContador.text=Numbercontar.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) { //revisar si el bundle es nulo solo si no se usa el onRestoreInstanceState

        Log.i("ciclo-vida","On Safe Instance State")
        outState.run{
            putInt(save_instance_state_contador,Numbercontar)
        }

        super.onSaveInstanceState(outState) //Invocar después de guardar el estado del usuario.
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val tieneContadorGuardado = savedInstanceState.get(save_instance_state_contador) as Int //

            Numbercontar = tieneContadorGuardado
            this.contar(0)

        super.onRestoreInstanceState(savedInstanceState)
        Log.i("ciclo-vida","On Restore Instance State")
    }

    companion object {
        val save_instance_state_contador = "contador"
    }


}
