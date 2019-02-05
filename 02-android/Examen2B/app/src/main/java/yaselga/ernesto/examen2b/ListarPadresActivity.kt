package yaselga.ernesto.examen2b

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_listar_padres.*
//import yaselga.ernesto.examen2b.BDD.Companion.Pacientes
import yaselga.ernesto.examen2b.BDD.Companion.Pacientes

class ListarPadresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_padres)

        val layoutManager = LinearLayoutManager(this)
        val rv = rv_so
        val adaptador = SistemaOpAdaptador(BDD.Pacientes, this, rv)

        rv_so.layoutManager = layoutManager
        rv_so.itemAnimator = DefaultItemAnimator()
        rv_so.adapter = adaptador

        adaptador.notifyDataSetChanged()

    }


    fun refrescar(){
        finish()
        val direccion = "http://${BDD.ip}:8000/sistemas/api/"
        Log.i("http",direccion)
        cargarDatosSO(direccion,fun(){})
        startActivity(getIntent());
    }

    fun compartir(contenido:String){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, contenido)
            type = "text/plain"
        }
        startActivity(sendIntent)
    }


    fun irActualizar(paciente: PacienteSe){
        val intentActividadIntent = Intent(
                this,
                CrearPadre::class.java
        )

        intentActividadIntent.putExtra("sistema", paciente)
        startActivity(intentActividadIntent)

    }

    fun irAlistarHijos(paciente: PacienteSe){

        "http://${BDD.ip}:8000/sistemas/api/app/?id=${paciente.id}".httpGet().responseString{ request, response, result ->
            Log.i("esponja", "http://${BDD.ip}:8000/sistemas/api/app/?so=${paciente.id}")
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http", ex.toString())
                }
                is Result.Success -> {
                    val data = result.get()
                    BDD.Medicamentos.clear()
                    val wordDict = Klaxon().parseArray<Medicamento>(data)
                    Log.i("http", "Datos: ${wordDict.toString()}")
                    if (wordDict != null) {
                        for ( item in wordDict.iterator()){
                            BDD.Medicamentos.add(item)
                        }
                    }

                    finish()
                    val intentActividadIntent = Intent(
                            this,
                            ListarHijosActivity::class.java
                    )

                    intentActividadIntent.putExtra("sistema", paciente)
                    startActivity(intentActividadIntent)
                }
            }
        }
    }
}

class SistemaOpAdaptador(private val listaPacientes: List<Paciente>,
                         private val contexto: ListarPadresActivity,
                         private val recyclerView: RecyclerView) :
        RecyclerView.Adapter<SistemaOpAdaptador.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var versionTextView: TextView
        var idSOTextView: TextView
        var opciones:Button

        init {
            nombreTextView = view.findViewById(R.id.txt_nombre_paciente) as TextView
            versionTextView = view.findViewById(R.id.txt_apellido_paciente) as TextView
            idSOTextView = view.findViewById(R.id.txt_paciente_id) as TextView
            opciones = view.findViewById(R.id.btn_opciones) as Button



            // val left = apellido.paddingLeft
            // val top = apellido.paddingTop
            // Log.i("vista-principal", "Hacia la izquierda es $left y hacia arriba es $top")

            val layout = view.findViewById(R.id.relative_layout_paciente) as RelativeLayout

            layout
                    .setOnClickListener {
                        val nombreActual = it.findViewById(R.id.txt_nombre_paciente) as TextView

                        Log.i("recycler-view",
                                "El nombre actual es: ${nombreActual.text}")

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
                        R.layout.item_padres,
                        parent,
                        false
                )

        return MyViewHolder(itemView)
    }

    // Llenamos los datos del layout
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sistema = listaPacientes[position]

        holder.nombreTextView.setText(sistema.nombre)
        holder.versionTextView.setText(sistema.apellido)
        holder.idSOTextView.setText(sistema.id.toString())
        holder.opciones.setOnClickListener {
            val popup = PopupMenu(contexto, holder.idSOTextView)
            popup.inflate(R.menu.options_menu)
            //adding click listener
            popup.setOnMenuItemClickListener { item ->
                when (item.getItemId()) {
                    R.id.eliminar_so ->{
                        //handle menu1 click
                        mensaje_dialogo(contexto,"Eliminar el SO?",
                                fun (){
                                    val id = holder.idSOTextView.text.toString()
                                    Log.i("Eliminar SO->",id)

                                    val direccion = "http://${BDD.ip}:8000/sistemas/api/"
                                    Log.i("http",direccion)
                                    val parametros = listOf("nombre" to id)
                                    val url = "http://${BDD.ip}:8000/sistemas/api/$id/delete"
                                            .httpDelete(parametros)
                                            .responseString { request, response, result ->
                                                when (result) {
                                                    is Result.Failure -> {
                                                        val ex = result.getException()
                                                        Log.i("http-p", ex.toString())
                                                        mensaje(contexto,"error","Datos no validos")

                                                    }
                                                    is Result.Success -> run {
                                                        val data = result.get()
                                                        Log.i("http-p", data)
                                                        mensaje(contexto,"Aceptado","Datos validos, espere...")
                                                        contexto.refrescar()
                                                    }
                                                }
                                            }




                                }
                        )



                        true
                    }

                    R.id.editar_so ->{
                        val id = holder.idSOTextView.text.toString()
                        mensaje_dialogo(contexto,"Desea editar el SO?",

                                fun(){
                                    val so = Pacientes.filter { it.id==id.toInt() }[0]
                                    Log.i("Actualizar SO->",so.fechaNacimiento)
                                    val soSerializado = PacienteSe(
                                            id.toInt(),
                                            nombre = so.nombre,
                                            apellido = so.apellido,
                                            fechaNacimiento = so.fechaNacimiento,
                                            hijos = so.hijos
                                    )
                                    contexto.irActualizar(soSerializado)
                                }

                        )

                        //handle menu2 click
                        true
                    }

                    R.id.compartir_so ->{
                        val nombre = holder.nombreTextView.text.toString()
                        contexto.compartir(nombre)
                        //handle menu3 click
                        true
                    }

                    R.id.medicamentos ->{
                        var direccion = ""
                        val id = holder.idSOTextView.text.toString()
                        val so = Pacientes.filter { it.id==id.toInt() }[0]
                        Log.i("Listar SO->",so.id.toString())
                        val soSerializado = PacienteSe(
                                id.toInt(),
                                nombre = so.nombre,
                                apellido = so.apellido,
                                fechaNacimiento = so.fechaNacimiento,
                                hijos = so.hijos
                        )
                        Log.i("Listar Se->",soSerializado.toString())
                        contexto.irAlistarHijos(soSerializado)
                        true
                    }


                    else -> false
                }
            }
            //displaying the popup
            popup.show()
        }
    }

    override fun getItemCount(): Int {
        return listaPacientes.size
    }


}










