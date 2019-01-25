package ernestoyaselga.fairbeis

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
import kotlinx.android.synthetic.main.activity_bar_code_reader.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class BarCodeReaderActivity : AppCompatActivity() {
    var pathActualFoto=""

    var respuestaBarcode=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_code_reader)
        btn_tomar_foto.setOnClickListener{
            tomarFoto()
        }
    }
    fun tomarFoto(){
        val archivoImagen = CrearArchivo("JPEG_",Environment.DIRECTORY_PICTURES,".jpg")
        pathActualFoto = archivoImagen.absolutePath
        enviarIntentFoto(archivoImagen)
    }

    private fun CrearArchivo(
            prefijo:String,
            directorio:String,
            extension:String): File {
        val timeStamp = SimpleDateFormat("yyyMMdd_HHmmss").format(Date())

        val prefijoImagen = prefijo+timeStamp+"_"

        val directorioAlmacenamiento = getExternalFilesDir(directorio)

        return File.createTempFile(
                prefijoImagen,/*prefijo*/
                extension,/*extension*/
                directorioAlmacenamiento/*directorio*/
        )

    }

    private fun enviarIntentFoto(archivo: File) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "ernestoyaselga.fairbeis",
                archivo)

        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

        if (takePictureIntent.resolveActivity(packageManager) != null) {

            startActivityForResult(takePictureIntent, TOMAR_FOTO_REQUEST);

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            TOMAR_FOTO_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    mostrarFotoImageView()
                    // obtenerInfoCodigoBarras(obtenerBitmapDeArchivo(mPathActualFoto))
                }
            }

        }
    }

    fun mostrarFotoImageView() {
        imageView_codigo_de_barras.setImageBitmap(obtenerBitmapDeArchivo(pathActualFoto))
    }

    fun obtenerBitmapDeArchivo(path: String): Bitmap {
        val archivoImagen = File(path)
        return BitmapFactory.decodeFile(archivoImagen.getAbsolutePath())
    }

    companion object {
        val TOMAR_FOTO_REQUEST = 1
    }
}
