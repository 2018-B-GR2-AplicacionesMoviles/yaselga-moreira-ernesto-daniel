package ernestoyaselga.a2018b_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.provider.AlarmClock



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boton_navegar
                .setOnClickListener {
                    /*
                    Log.i("navegacion","Hola")
                    Log.w("navegacion","Hola")
                    Log.d("navegacion","Hola")
                    Log.e("navegacion","Hola")
                    Log.v("navegacion","Hola")
                    */
                    this.irAPantallaDeBotones()
                }
        boton_alarma.setOnClickListener{
            this.createAlarm("Alarm",6,0)

        }
        boton_respuesta.setOnClickListener{
            this.irAPantallaRespuesta()
        }

        btn_cicloDeVida.setOnClickListener {
            this.irACicloDeVida()
        }
    }

    fun irACicloDeVida(){
        val intentIrACicloDeVida = Intent(this, CicloDeVidaActivity::class.java)
        this.startActivity(intentIrACicloDeVida)
    }

    fun irAPantallaDeBotones() {
        // INTENT
        val intentIrABotones = Intent(this, ButtonActivity::class.java)
                intentIrABotones.putExtra("nombre","Ernesto")
                intentIrABotones.putExtra("apellido","Yaselga")
        this.startActivity(intentIrABotones)
    }
    fun irAPantallaRespuesta() {
        // INTENT
        val intentIrARespuesta = Intent(this, Respuesta_Activity::class.java)
        this.startActivity(intentIrARespuesta)
    }

    fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
