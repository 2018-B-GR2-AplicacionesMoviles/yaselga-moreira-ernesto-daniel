package ernestoyaselga.a2018b_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

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
        button_intent_respuesta
                .setOnClickListener {
                    this.irAPantallaDeIntentRespuesta()
                }

        button_ciclo_vida
                .setOnClickListener {
                    this.irAPantallaCicloVida()
                }

        button_intent_parcelable
                .setOnClickListener {
                    this.irActividadParcelableIntent()
                }

        button_adaptador
                .setOnClickListener {
                    this.irActividadAdaptador()
                }

        button_recycler_view
                .setOnClickListener {
                    this.irActividadRecyclerView()
                }
    }

    fun irActividadRecyclerView() {
        val intent = Intent(
                this,
                ReciclerViewActivity::class.java
        )
        startActivity(intent)
    }

    fun irActividadAdaptador() {
        val intentAdaptador = Intent(
                this,
                AdaptadorActivity::class.java
        )
        startActivity(intentAdaptador)
    }

    fun irActividadParcelableIntent() {
        val intentActividadIntent = Intent(
                this,
                ParcelableActivity::class.java
        )

        val adrian = Usuario(
                "Adrian",
                29,
                Date(1989, 6, 10),
                12.00)

        val cachetes = Mascota("Cachetes", adrian)

        intentActividadIntent.putExtra("usuario", adrian)
        intentActividadIntent.putExtra("mascota", cachetes)

        startActivity(intentActividadIntent)


    }

    fun irAPantallaCicloVida() {
        val intentCicloVida = Intent(
                this,
                CicloVidaActivity::class.java
        )
        this.startActivity(intentCicloVida)
    }

    fun irAPantallaDeBotones() {
        // INTENT
        val intentIrABotones = Intent(this, ButtonActivity::class.java)
        intentIrABotones.putExtra("nombre", "Adrian")
        intentIrABotones.putExtra("apellido", "Eguez")
        this.startActivity(intentIrABotones)
    }

    fun irAPantallaDeIntentRespuesta() {
        // INTENT
        val intentRespuesta = Intent(this, IntentRespuestaActivity::class.java)
        this.startActivity(intentRespuesta)
    }

}
