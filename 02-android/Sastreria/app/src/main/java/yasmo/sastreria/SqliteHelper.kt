package yasmo.sastreria

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
//import com.example.andres.ezmoviesadmin.dummy.PeliculaContent
//import com.example.andres.ezmoviesadmin.dummy.PeliculaContent.Pelicula


class SqliteHelper(context: Context?) :
        SQLiteOpenHelper(context,
                "yasmoDB", // Nombre de la base de datos
                null,
                1) {

    override fun onCreate(baseDeDatos: SQLiteDatabase?) {

        val crearTablaCliente = "CREATE TABLE " +
                "cliente " +
                "(" +
                "id_cliente VARCHAR(10) PRIMARY KEY, " +
                "nombre VARCHAR(50)," +
                "telefono VARCHAR(15),"+
                ")"

        val crearTablaPrenda = "CREATE TABLE " +
                "prenda" +
                "(" +
                "id_prenda INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre VARCHAR(50)" +
                "material VARCHAR(50)" +
                "precio money" +
                ")"
        val crearTablaPedido = "CREATE TABLE " +
                "pedido" +
                "(" +
                "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FOREIGN KEY(id_cliente) REFERENCES cliente_id(id_cliente)" +
                "FOREIGN KEY(id_prenda) REFERENCES prenda_id(id_prenda)" +
                "fecha_entrega date" +
                ")"

        val insertarPrenda1 = "Insert into prenda (nombre,material,precio) values ('Camisa','algodon','20.50');"
        val insertarPrenda2 = "Insert into prenda (nombre,material,precio) values ('Camisa','lana','30.50');"
        val insertarPrenda3 = "Insert into prenda (nombre,material,precio) values ('Camisa','poliester','25.50');"
        val insertarPrenda4 = "Insert into prenda (nombre,material,precio) values ('Pantalon','algodon','17.50');"
        val insertarPrenda5 = "Insert into prenda (nombre,material,precio) values ('Pantalon','poliester','20.50');"

        val insertarCliente = "Insert into cliente (id_cliente,nombre,telefono) values ('1725158123','Ernesto Yaselga','0995193611');"

        baseDeDatos?.execSQL(crearTablaCliente)
        baseDeDatos?.execSQL(crearTablaPrenda)
        baseDeDatos?.execSQL(crearTablaPedido)

        baseDeDatos?.execSQL(insertarCliente)
        baseDeDatos?.execSQL(insertarPrenda1)
        baseDeDatos?.execSQL(insertarPrenda2)
        baseDeDatos?.execSQL(insertarPrenda3)
        baseDeDatos?.execSQL(insertarPrenda4)
        baseDeDatos?.execSQL(insertarPrenda5)

    }

    override fun onUpgrade(baseDeDatos: SQLiteDatabase?,
                           antiguaVersion: Int,
                           nuevaVersion: Int) {

    }

    fun getCliente(clientes: ArrayList<Cliente>){
        val statement = "select id_cliente, nombre, telefono from cliente;"
        val dbReadable = readableDatabase
        val resultado = dbReadable.rawQuery(statement, null)
        clientes.clear()

        if (resultado.moveToFirst()) {
            do {
                clientes.add(Cliente(resultado.getString(0),resultado.getString(1),resultado.getString((2))))
            } while (resultado.moveToNext())
        }

        resultado.close()
        dbReadable.close()
    }

    /*
    fun getPeliculas(peliculas: ArrayList<PeliculaContent.Pelicula>){
        val statement = "select id_pelicula, pelicula.nombre, pelicula.id_categoria, categoria.nombre from pelicula,categoria where pelicula.id_categoria=categoria.id_categoria;"

        val dbReadable = readableDatabase

        val resultado = dbReadable.rawQuery(statement, null)
        Log.i("BDD", resultado.getColumnName(0))

        peliculas.clear()

        if (resultado.moveToFirst()) {
            do {
                peliculas.add(Pelicula(resultado.getString(0),resultado.getString(1),resultado.getString(2),resultado.getString(3)))
            } while (resultado.moveToNext())
        }

        resultado.close()

        dbReadable.close()


    }
*/
    fun insertarCliente(id_cliente: String,
                        nombre: String,
                        telefono: String): Boolean {
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos
        cv.put("id_cliente", id_cliente)
        cv.put("nombre", nombre)
        cv.put("telefono", telefono)

        val resultado: Long = dbWriteable
                .insert(
                        "cliente", // Nombre de la tabla
                        null,
                        cv)

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true

    }
/* //CREAR EL INGRESO DE LOS OTROS 2
    fun crearCategoriaForumlario(nombre: String): Boolean {
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos
        cv.put("nombre", nombre)

        val resultado: Long = dbWriteable
                .insert(
                        "categoria", // Nombre de la tabla
                        null,
                        cv)

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true

    }
    fun crearActorForumlario(nombre: String): Boolean {
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos
        cv.put("nombre", nombre)

        val resultado: Long = dbWriteable
                .insert(
                        "actor", // Nombre de la tabla
                        null,
                        cv)

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true

    }*/

    fun actualizarCliente(id:String,nombre: String,telefono: String):Boolean{
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos

        cv.put("id_cliente", id)
        cv.put("nombre", nombre)
        cv.put("telefono", telefono)

        val resultado = dbWriteable
                .update(
                        "cliente", // Nombre de la tabla
                        cv, // Valores a actualizarse
                        "id_cliente=?", // Where
                        arrayOf(id) // Parametros
                )

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true
    }
/*
    fun actualizarCategoria(id:String,nombre: String):Boolean{
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos

        cv.put("nombre", nombre)

        val resultado = dbWriteable
                .update(
                        "categoria", // Nombre de la tabla
                        cv, // Valores a actualizarse
                        "id_categoria=?", // Where
                        arrayOf(id) // Parametros
                )

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true
    }*/

    fun eliminarCliente(id:String):Boolean{
        val dbWriteable = this.writableDatabase
        val parametros = arrayOf(id)
        val nombreTabla = "cliente"
        val clausulaWhere = "id_cliente = ?"
        val respuesta = dbWriteable.delete(
                nombreTabla,
                clausulaWhere,
                parametros
        )
        return  if(respuesta == -1 )false else true
    }

    /*fun eliminarCategoria(id:String):Boolean{
        val dbWriteable = this.writableDatabase
        val parametros = arrayOf(id)
        val nombreTabla = "categoria"
        val clausulaWhere = "id_categoria = ?"
        val respuesta = dbWriteable.delete(
                nombreTabla,
                clausulaWhere,
                parametros
        )
        return  if(respuesta == -1 )false else true
    }*/


}






