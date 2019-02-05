package yaselga.ernesto.examen2b

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.android.synthetic.main.activity_crear_hijo.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CrearHijo : AppCompatActivity() {
    var pathActualFoto = ""
    var respuestaBarcode = ArrayList<String>()
    var id_so=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_hijo)
        btn_tomar_foto.setOnClickListener {
            tomarFoto()
        }

        val medicamento = intent.getParcelableExtra<Medicamento?>("medicamento")
        val sistemaOperativo = intent.getParcelableExtra<Medicamento?>("sistema")


        id_so=intent.getIntExtra("id_so",0)
        //val sistema: SistemaOperativo? = null
        var esnuevo = true

        if (medicamento != null) {
            txt_id_app.setText(medicamento.IDMed.toString())
            txt_nombre_app.setText(medicamento.nombre)
            txt_version_app.setText(medicamento.composicion)
            txt_fecha_app.setText(medicamento.fechaCaducidad)
            txt_peso_app.setText(medicamento.gramosAingerir)
            txt_costo_app.setText(medicamento.numeroPastillas)
            txt_url_download.setText(medicamento.usadoPara)
            txt_codigo.setText(medicamento.codigo_barras)
            id_so=medicamento.paciente
            esnuevo = false
        }



        btn_guardar_app.setOnClickListener {
            if(esnuevo){
                crearActualizarAPP(true)
            }else{
                crearActualizarAPP(false)
            }
        }

        btn_cancelar_app.setOnClickListener {
            val redire = "http://${BDD.ip}:8000/sistemas/api/app/?so=$id_so"
            cargarDatosHijo(redire,::irlistarApp)
        }


    }


    fun irlistarApp(){
        finish()
        val intent = Intent(
                this,
                ListarHijosActivity::class.java
        )
        intent.putExtra("id_so",id_so)
        startActivity(intent)
    }

    fun tomarFoto(){
        val archivoImagen = crearArchivo("JPEG_", Environment.DIRECTORY_PICTURES, ".jpg")
        pathActualFoto = archivoImagen.absolutePath
        enviarIntentFoto(archivoImagen)
    }

    private fun crearArchivo(prefijo: String, directorio: String, extension: String): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = prefijo + timeStamp + "_"
        val storageDir = getExternalFilesDir(directorio)

        // Crear archivo temporal
        return File.createTempFile(
                imageFileName, /* prefix */
                extension, /* suffix */
                storageDir      /* directory */
        )
    }

    private fun enviarIntentFoto(archivo: File) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.andres.examenapp2",
                archivo)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, TOMAR_FOTO_REQUEST);
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            TOMAR_FOTO_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    mostrarFotoImagenView()
                    obtenerInfoCodigoBarras(obtenerBitmapDeArchivo(pathActualFoto))
                }
            }

        }
    }

    fun mostrarFotoImagenView(){
        img_barras.setImageBitmap(obtenerBitmapDeArchivo(pathActualFoto))
    }

    fun obtenerBitmapDeArchivo(path: String): Bitmap {
        val archivoImagen = File(path)
        return BitmapFactory.decodeFile(archivoImagen.getAbsolutePath())
    }

    fun obtenerInfoCodigoBarras(bitmap: Bitmap) {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val detector = FirebaseVision.getInstance()
                .visionBarcodeDetector
        Log.i("info", "------- Entro a detectar")
        val result = detector.detectInImage(image)
                .addOnSuccessListener { barCodes ->
                    Log.i("info", "------- tamano del barcode ${barCodes.size}")
                    respuestaBarcode.add("Ejemplo")
                    for (barcode in barCodes) {
                        val bounds = barcode.getBoundingBox()
                        val corners = barcode.getCornerPoints()

                        val rawValue = barcode.getRawValue()

                        Log.i("ml", "------- $bounds")
                        Log.i("ml", "------- $corners")
                        Log.i("ml", "------- $rawValue")

                        respuestaBarcode.add(rawValue.toString())
                    }
                    txt_codigo.setText(respuestaBarcode[1])
                }
                .addOnFailureListener {
                    Log.i("info", "------- No reconocio nada")
                }
    }

    fun crearActualizarAPP(es_nuevo:Boolean){

        val id = txt_id_app.text.toString()
        val nombre = txt_nombre_app.text.toString()
        val version = txt_version_app.text.toString()
        val fecha = txt_fecha_app.text.toString()
        val peso = txt_peso_app.text.toString()
        val costo = txt_costo_app.text.toString().toInt()
        val url_descargar = txt_url_download.text.toString()
        val codigo_barras = txt_codigo.text.toString()

        val app:Medicamento
        if(es_nuevo){
            app = Medicamento(IDMed= null,nombre = nombre,composicion=  version,fechaCaducidad=  fecha,gramosAingerir=  peso,numeroPastillas= costo,usadoPara= url_descargar,codigo_barras = codigo_barras,paciente= id_so)
        }else{
            app = Medicamento(IDMed= id.toInt(),nombre = nombre,composicion=  version,fechaCaducidad=  fecha,gramosAingerir=  peso,numeroPastillas= costo,usadoPara= url_descargar,codigo_barras = codigo_barras,paciente= id_so)
        }

        //Crear objeto
        val parametros = listOf(
                "nombre" to app.nombre,
                "version" to app.composicion,
                "fechaLanzamiento" to app.fechaCaducidad,
                "peso_gigas" to app.gramosAingerir,
                "costo" to app.numeroPastillas,
                "url_descargar" to app.usadoPara,
                "codigo_barras" to app.codigo_barras,
                "sistemaOperativo" to app.paciente
        )
        Log.i("htpp",parametros.toString())
        var direccion = ""
        if(es_nuevo){
            direccion = "http://${BDD.ip}:8000/sistemas/api/app/"
            val url = direccion
                    .httpPost(parametros)
                    .responseString { request, response, result ->
                        when (result) {
                            is Result.Failure -> {
                                val ex = result.getException()
                                Log.i("http-p", ex.toString())
                                mensaje(this,"error","Datos no validos")

                            }
                            is Result.Success -> run {
                                val data = result.get()
                                Log.i("http-p", data)
                                mensaje(this,"Aceptado","Datos validos, espere...")
                                val redire = "http://${BDD.ip}:8000/sistemas/api/app/?so=$id_so"
                                cargarDatosHijo(redire,::irlistarApp)
                            }
                        }
                    }
        }else{
            direccion = "http://${BDD.ip}:8000/sistemas/api/app/${app.IDMed}/update"
            val url = direccion
                    .httpPut(parametros)
                    .responseString { request, response, result ->
                        when (result) {
                            is Result.Failure -> {
                                val ex = result.getException()
                                Log.i("http-p", ex.toString())
                                mensaje(this,"error","Datos no validos")

                            }
                            is Result.Success -> run {
                                val data = result.get()
                                Log.i("http-p", data)
                                mensaje(this,"Aceptado","Datos validos, espere...")
                                val redire = "http://${BDD.ip}:8000/sistemas/api/app/?so=$id_so"
                                cargarDatosHijo(redire,::irlistarApp)
                            }
                        }
                    }
        }
        Log.i("http",direccion)

    }

    companion object {
        val TOMAR_FOTO_REQUEST = 1;
    }
}
