package yasmo.sastreria

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_catalogo.*

class Catalogo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)

        val layoutManager = LinearLayoutManager(this)
        val rv = catalogo_recicler_view
        val adaptador = PrendasAdaptador(BDD.prendas, this, rv)

        catalogo_recicler_view.layoutManager = layoutManager
        catalogo_recicler_view.itemAnimator = DefaultItemAnimator()
        catalogo_recicler_view.adapter = adaptador

        adaptador.notifyDataSetChanged()

        BDD.crearMas()

    }
}

class Prenda (var nombre: String, var material: String, var precio:Double) {}

class PrendasAdaptador(private val listaPrendas: List<Prenda>,
                        private val contexto: Catalogo,
                        private val recyclerView: RecyclerView) :
        RecyclerView.Adapter<PrendasAdaptador.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var materialTextView: TextView
        var precioTextView: TextView

        init {
            nombreTextView = view.findViewById(R.id.catalogo_textView_nombre) as TextView
            materialTextView = view.findViewById(R.id.catalogo_textView_material) as TextView
            precioTextView = view.findViewById(R.id.catalogo_textView_precio) as TextView

           // val layout = view.findViewById(R.id.relative_layout) as RelativeLayout

            /*layout.setOnClickListener {
                        val nombreActual = it.findViewById(R.id.catalogo_textView_nombre) as TextView

                        Log.i("recycler-view",
                                "El nombre actual es: ${nombreActual.text}")

                    }*/

            val boton = view.findViewById(R.id.catalogo_button_ver) as Button

            boton.setOnClickListener {
                val toast = Toast.makeText(
                        it.context,
                        "Actualmente no disponible ${nombreTextView.text}",
                        Toast.LENGTH_LONG
                )//cambiar por la funcion de crear pedidos
                toast.show()

                //BDD.crearMas()
                val layoutManager = LinearLayoutManager(contexto)
                val adaptador = PrendasAdaptador(BDD.prendas, contexto, recyclerView)

                recyclerView.layoutManager = layoutManager
                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.adapter = adaptador

                adaptador.notifyDataSetChanged()
            }

        }
    }

    // Definimos el layout
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int): MyViewHolder {

        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(
                        R.layout.catalogo_prendas_layout,
                        parent,
                        false
                )

        return MyViewHolder(itemView)
    }

    // Llenamos los datos del layout
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val prenda= listaPrendas[position]

        holder.nombreTextView.setText(prenda.nombre)
        holder.materialTextView.setText(prenda.material)
        holder.precioTextView.setText(prenda.precio.toString())
    }

    override fun getItemCount(): Int {
        return listaPrendas.size
    }
}
class BDD {
    companion object {
        val prendas = ArrayList<Prenda>()

        fun crearMas() {
            prendas.add(Prenda("Pantalon", "tela",45.00))
            prendas.add(Prenda("Camisa", "tela",35.00))
            prendas.add(Prenda("Chaleco", "tela",25.00))
            prendas.add(Prenda("Leva", "tela",40.00))
        }
    }
}
