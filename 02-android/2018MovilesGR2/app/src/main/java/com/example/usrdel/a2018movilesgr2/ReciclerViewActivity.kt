package com.example.usrdel.a2018movilesgr2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
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
import kotlinx.android.synthetic.main.activity_recicler_view.*

class ReciclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recicler_view)

        BDD.crearMas()

        val layoutManager = LinearLayoutManager(this)
        val rv = recycler_view
        val adaptador = PersonasAdaptador(BDD.usuarios, this, rv)

        recycler_view.layoutManager = layoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = adaptador

        adaptador.notifyDataSetChanged()

    }
}


class Persona(var nombre: String, var cedula: String) {}


class PersonasAdaptador(private val listaPersonas: List<Persona>,
                        private val contexto: ReciclerViewActivity,
                        private val recyclerView: RecyclerView) :
        RecyclerView.Adapter<PersonasAdaptador.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var cedulaTextView: TextView

        init {
            nombreTextView = view.findViewById(R.id.text_view_nombre) as TextView
            cedulaTextView = view.findViewById(R.id.text_view_cedula) as TextView


            // val left = apellido.paddingLeft
            // val top = apellido.paddingTop
            // Log.i("vista-principal", "Hacia la izquierda es $left y hacia arriba es $top")

            val layout = view.findViewById(R.id.relative_layout) as RelativeLayout

            layout
                    .setOnClickListener {
                        val nombreActual = it.findViewById(R.id.text_view_nombre) as TextView

                        Log.i("recycler-view",
                                "El nombre actual es: ${nombreActual.text}")

                    }

            val boton = view.findViewById(R.id.button_ver_info) as Button

            boton
                    .setOnClickListener {
                        val toast = Toast.makeText(
                                it.context,
                                "Hola ${nombreTextView.text}",
                                Toast.LENGTH_LONG
                        )
                        toast.show()

                        BDD.crearMas()
                        val layoutManager = LinearLayoutManager(contexto)
                        val adaptador = PersonasAdaptador(BDD.usuarios, contexto, recyclerView)

                        recyclerView.layoutManager = layoutManager
                        recyclerView.itemAnimator = DefaultItemAnimator()
                        recyclerView.adapter = adaptador

                        adaptador.notifyDataSetChanged()
                    }


            /*
            layout
                    .setOnClickListener({ v ->
                        val nombreActual = v.findViewById(R.id.text_view_nombre) as TextView

                        Log.i("recycler-view",
                                "El nombre actual es: ${nombreActual.text}")
                        // nombreActual.text = "Otro texto"

                    })
            */
            /*
                cedula.setOnClickListener { v ->
                    val cedulaActual = v.findViewById(R.id.txtv_cedula) as TextView
                    val toast = Toast.makeText(v.context, "Hola ${cedulaActual.text}", Toast.LENGTH_LONG)
                    toast.show()
                    val intent = Intent(v.context, ActividadLayouts::class.java)
                    startActivity(v.context, intent, null)
                }
                */
        }
    }

    // Definimos el layout
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int): MyViewHolder {

        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(
                        R.layout.lista_recycler_view_layout,
                        parent,
                        false
                )

        return MyViewHolder(itemView)
    }

    // Llenamos los datos del layout
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val persona = listaPersonas[position]

        holder.nombreTextView.setText(persona.nombre)
        holder.cedulaTextView.setText(persona.cedula)
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }

}

class BDD {
    companion object {
        val usuarios = ArrayList<Persona>()

        fun crearMas() {
            usuarios.add(Persona("Juan", "353331223"))
            usuarios.add(Persona("Adrian", "12312312"))
            usuarios.add(Persona("Vicente", "98734833"))
        }
    }
}







