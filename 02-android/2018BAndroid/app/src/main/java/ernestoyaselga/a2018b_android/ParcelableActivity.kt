package ernestoyaselga.a2018b_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ParcelableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable)

        var usuario = intent.getParcelableExtra<Usuario?>("usuario")
        var mascota = intent.getParcelableExtra<Mascota?>("cuatro")
        if(usuario!= null&&mascota!=null){
            Log.i("Parcelable", "usuario ${usuario.nombre}")
            Log.i("Parcelable", "usuario ${mascota.nombre}")
        }



    }

}
