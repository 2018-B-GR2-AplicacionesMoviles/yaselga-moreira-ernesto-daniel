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
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_listar_hijos.*
import yaselga.ernesto.examen2b.BDD.Companion.Medicamentos

//import yaselga.ernesto.examen2b.BDD.Companion.Medicamentos

class ListarHijosActivity : AppCompatActivity() {
    var id_so = 0
    var id_res = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_hijos)
        id_res = intent.getIntExtra("id_so",0)

        var sistema:PacienteSe
        if(id_res ==0){
            sistema = intent.getParcelableExtra<PacienteSe>("sistema")
        }else{
            var so = BDD.Pacientes.filter { it.id==id_res }[0]
            sistema= PacienteSe(
                    id_res,
                    nombre = so.nombre,
                    apellido = so.apellido,
                    fechaNacimiento = so.fechaNacimiento,
                    hijos = so.hijos
            )
        }

        id_so = sistema.id!!


        txt_nombre_cliente_parce.setText(sistema.nombre)
        txt_apellido_parce.setText(sistema.apellido)

        btn_nuevo_medicamento
                .setOnClickListener {
                    irACrearHijo()
                }

        val layoutManager = LinearLayoutManager(this)
        val rv = rv_hijos
        val adaptador = AppAdaptador(BDD.Medicamentos, this, rv)

        rv_hijos.layoutManager = layoutManager
        rv_hijos.itemAnimator = DefaultItemAnimator()
        rv_hijos.adapter = adaptador

        adaptador.notifyDataSetChanged()

    }


    fun refrescar(){
        finish()
        val direccion = "http://${BDD.ip}:8000/sistemas/api/app/?so=$id_so"
        Log.i("http",direccion)
        cargarDatosApp(direccion,fun(){})
        startActivity(getIntent())
    }

    fun compartir(contenido:String){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, contenido)
            type = "text/plain"
        }
        startActivity(sendIntent)
    }


    fun irActualizar(medicamento: MedicamentoSe){
        val intentActividadIntent = Intent(
                this,
                CrearHijo::class.java
        )

        intentActividadIntent.putExtra("Medicamento",medicamento)
        startActivity(intentActividadIntent)

    }

    fun irACrearHijo(){
        finish()
        val intentActividadIntent = Intent(
                this,
                CrearHijo::class.java
        )
        intentActividadIntent.putExtra("id_so",id_so)
        startActivity(intentActividadIntent)
    }
}




class AppAdaptador(private val listaMedicamentos: List<Medicamento>,
                   private val contexto: ListarHijosActivity,
                   private val recyclerView: RecyclerView) :
        RecyclerView.Adapter<AppAdaptador.MyViewHolder>() {


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var versionTextView: TextView
        var idAppTextView: TextView
        var opciones:Button

        init {
            nombreTextView = view.findViewById(R.id.txt_nombre_med) as TextView
            versionTextView = view.findViewById(R.id.txt_composicion_med) as TextView
            idAppTextView = view.findViewById(R.id.txt_med_id) as TextView
            opciones = view.findViewById(R.id.btn_opciones_app) as Button



            // val left = apellido.paddingLeft
            // val top = apellido.paddingTop
            // Log.i("vista-principal", "Hacia la izquierda es $left y hacia arriba es $top")

            val layout = view.findViewById(R.id.relative_lay_list_hijos) as RelativeLayout

            layout
                    .setOnClickListener {
                        val nombreActual = it.findViewById(R.id.txt_nombre_med) as TextView

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
                        R.layout.items_hijos,
                        parent,
                        false
                )

        return MyViewHolder(itemView)
    }

    // Llenamos los datos del layout
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val aplicacion = listaMedicamentos[position]

        holder.nombreTextView.setText(aplicacion.nombre)
        holder.versionTextView.setText(aplicacion.composicion)
        holder.idAppTextView.setText(aplicacion.id.toString())
        holder.opciones.setOnClickListener {
            val popup = PopupMenu(contexto, holder.idAppTextView)
            popup.inflate(R.menu.options_menu)
            //adding click listener
            popup.setOnMenuItemClickListener { item ->
                when (item.getItemId()) {
                    R.id.eliminar_so ->{
                        //handle menu1 click
                        mensaje_dialogo(contexto,"Eliminar la APP?",
                                fun (){
                                    val id = holder.idAppTextView.text.toString()
                                    Log.i("Eliminar APP->",id)

                                    val parametros = listOf("nombre" to id)
                                    val url = "http://${BDD.ip}:8000/sistemas/api/app/$id/delete"
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
                        val id = holder.idAppTextView.text.toString()
                        mensaje_dialogo(contexto,"Desea editar la Medicamento?",
                                fun(){
                                    val app = Medicamentos.filter { it.id==id.toInt() }[0]
                                    Log.i("Actualizar SO->",app.fechaCaducidad)
                                    val appSerializada = MedicamentoSe(
                                            id.toInt(),
                                            nombre = app.nombre,
                                            composicion = app.composicion,
                                            fechaCaducidad = app.fechaCaducidad,
                                            gramosAingerir = app.gramosAingerir,
                                            numeroPastillas = app.numeroPastillas,
                                            usadoPara = app.usadoPara,
                                            codigo_barras = app.codigo_barras,
                                            paciente = app.paciente!!
                                    )
                                    contexto.irActualizar(appSerializada)

                                })

                        //handle menu2 click
                        true
                    }

                    R.id.compartir_so ->{
                        val nombre = holder.nombreTextView.text.toString()
                        contexto.compartir(nombre)
                        //handle menu3 click
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
        return listaMedicamentos.size
    }


}










