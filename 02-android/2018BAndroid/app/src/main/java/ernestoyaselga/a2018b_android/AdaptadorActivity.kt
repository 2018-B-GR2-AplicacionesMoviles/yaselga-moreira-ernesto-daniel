package ernestoyaselga.a2018b_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_adaptador.*
import java.util.*

class AdaptadorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adaptador)

        val arregloUsuarios = ArrayList<Usuario>();

        val usuarioUno = Usuario("Ernesto",22,Date(1996,10,28),18.00)
        val usuarioDos = Usuario("Daniel",12,Date(2006,10,28),18.00)

        arregloUsuarios.add(usuarioUno)
        arregloUsuarios.add(usuarioDos)

        val arregloMascotas = ArrayList<Mascota>();
        val opcionuno = Mascota("Firulais",usuarioUno)
        val opciondos = Mascota("Pelusa",usuarioDos)
        val opciontres = Mascota("Zeus",usuarioDos)

        arregloMascotas.add(opcionuno)
        arregloMascotas.add(opciondos)
        arregloMascotas.add(opciontres)



        //ADAPTADOR
        //CREAR
        val adaptadorUsuario = ArrayAdapter<Usuario>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                arregloUsuarios
        )

        val adaptadorLista = ArrayAdapter<Mascota>(
                this,
                android.R.layout.simple_list_item_1,
                arregloMascotas)




        //SETEAR EL ADAPTADOR
        spinner_usuario.adapter = adaptadorUsuario

        lista_mascotas.adapter = adaptadorLista



        //ESCUCHAR LOS EVENTOS DEL MISMO
        /*spinner_usuario.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long) {
                Log.i("adaptador","${parent}")
                Log.i("adaptador","${view}")
                Log.i("adaptador","${position}")
                Log.i("adaptador","${id}")
                val usuario = arregloUsuarios[position]
                Log.i("adaptador","${usuario.nombre}")

            }

            override fun onNothingSelected(
                    parent: AdapterView<*>?) {
                Log.i("adaptador","${parent}")
            }

        }*/





    }
}
