package yasmo.sastreria

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.provider.ContactsContract
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_clientes.*

class Clientes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientes)

        ClientesDB.crearMas()

        val layoutManager = LinearLayoutManager(this)
        val rv = clientes_recyclerView

        val adaptador = PersonasAdaptador(ClientesDB.clientes, this, rv)
        clientes_recyclerView.layoutManager = layoutManager
        clientes_recyclerView.itemAnimator = DefaultItemAnimator()
        clientes_recyclerView.adapter = adaptador

        adaptador.notifyDataSetChanged()

        clientes_button_create.setOnClickListener {
            crearCliente()
        }
    }

    fun crearCliente(){
        var nuevoCliente = Persona(clientes_editText_nombre.text.toString(), clientes_editText_id.text.toString(), clientes_editText_telefono.text.toString())
        ClientesDB.clientes.add(nuevoCliente)

    }
    fun borrarCliente(){
        var ClienteCondenado = Persona(clientes_editText_nombre.text.toString(), clientes_editText_id.text.toString(), clientes_editText_telefono.text.toString())
        ClientesDB.clientes.remove(ClienteCondenado)
    }
    fun editarCliente(){
        //leer el obtenido del llenado de datos
        var ClienteCondenado = Persona(clientes_editText_nombre.text.toString(), clientes_editText_id.text.toString(), clientes_editText_telefono.text.toString())
        ClientesDB.clientes.add(ClienteCondenado)
    }

}

//class Persona(var nombre: String, var cedula: String, var telefono:String) {}




class PersonasAdaptador(private val listaPersonas: List<Persona>,
                        private val contexto: Clientes,
                        private val recyclerView: RecyclerView) :
        RecyclerView.Adapter<PersonasAdaptador.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var cedulaTextView: TextView
        var telefonoTextView: TextView

        var cliente = listaPersonas


        init {
            nombreTextView = view.findViewById(R.id.cliente_textView_nombre) as TextView
            cedulaTextView = view.findViewById(R.id.cliente_textView_id) as TextView
            telefonoTextView = view.findViewById(R.id.cliente_textView_telefono) as TextView


            val layout = view.findViewById(R.id.cliente_relative_layout) as RelativeLayout

            layout
                    .setOnClickListener {
                        val nombreActual = it.findViewById(R.id.cliente_textView_nombre) as TextView

                        Log.i("recycler-view",
                                "El nombre actual es: ${nombreActual.text}")

                    }

            val boton = view.findViewById(R.id.cliente_imageButton) as ImageButton

            boton
                    .setOnClickListener {
                        //clientes_editText_id cedulaTextView.text

                        val toast = Toast.makeText(
                                it.context,
                                "Hola ${nombreTextView.text}",
                                Toast.LENGTH_LONG
                        )
                        toast.show()


                        //ClientesDB.crearMas()
                        val layoutManager = LinearLayoutManager(contexto)
                        val adaptador = PersonasAdaptador(ClientesDB.clientes, contexto, recyclerView)

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
                        R.layout.clientes_layout,
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
        holder.telefonoTextView.setText(persona.telefono)

    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }

}
data class Persona(var nombre: String, var cedula: String, var telefono:String):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(nombre)
        dest?.writeString(cedula)
        dest?.writeString(telefono)
    }

    override fun describeContents(): Int {
        return  0

    }

    companion object CREATOR : Parcelable.Creator<Persona> {
        override fun newArray(size: Int): Array<Persona?> {
            return arrayOfNulls(size)
        }

        override fun createFromParcel(source: Parcel?): Persona {
            return Persona(source!!)
        }

    }
}

class ClientesDB{
    companion object {
        val clientes = ArrayList<Persona>()

        fun addCliente(cliente: Persona){
            clientes.add(cliente)
        }

        fun crearMas() {
            clientes.add(Persona("Juan Perez",      "153331XXXX", "0955487652"))
            clientes.add(Persona("Ernesto Yaselga", "172515XXXX","0995193611"))
            clientes.add(Persona("Daniel Moreira",  "172551XXXX", "0997788621"))
        }
    }
}
