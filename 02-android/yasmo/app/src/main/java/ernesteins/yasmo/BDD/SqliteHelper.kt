package ernesteins.yasmo.BDD

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ernesteins.yasmo.Clientes.Cliente
import ernesteins.yasmo.Catalogo.Prenda
import ernesteins.yasmo.Pedidos.Pedido

//import com.example.andres.ezmoviesadmin.dummy.PeliculaContent
//import com.example.andres.ezmoviesadmin.dummy.PeliculaContent.Pelicula


class SqliteHelper(context: Context?) :
        SQLiteOpenHelper(context,
                "yasmoDB", // Nombre de la base de datos
                null,
                1) {

    override fun onCreate(baseDeDatos: SQLiteDatabase?) {

        val crearTablaCliente = "CREATE TABLE " +
                "cliente" +
                "(" +
                "id_cliente VARCHAR(10) PRIMARY KEY, " +
                "nombre VARCHAR(100)," +
                "telefono VARCHAR(15)" +
                ")"

        val crearTablaPrenda = "CREATE TABLE " +
                "catalogo" +
                "(" +
                "id_prenda INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "descripcion VARCHAR(50)," +
                "material VARCHAR(50)," +
                "color VARCHAR(20)" +
                ")"

        val crearTablaPedido = "CREATE TABLE " +
                "pedido" +
                "(" +
                "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_cliente INTEGER,"+
                "id_prenda INTEGER,"+
                "precio_total INT" +
                ")"

        /*val crearTablaPedido = "CREATE TABLE " +
                "pedido" +
                "(" +
                "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cliente INTEGER,"+
                //"FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente)," +
                "fecha_pedido DATE," +
                "fecha_entrega DATE," +
                "precio_total INT" +
                ")"
        val crearTablaDetalle = "CREATE TABLE " +
                "detalle" +
                "(" +
                "id_detalle INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FOREIGN KEY(id_pedido) REFERENCES pedido(id_pedido)," +
                "FOREIGN KEY(id_prenda) REFERENCES prenda(id_prenda)," +
                "cantidad INT," +
                "precio_unit INT" +
                ")"*/

        val insertarcatalogo1 = "Insert into catalogo (descripcion,material,color) values ('Camisa','algodon','blanco');"
        val insertarcatalogo2 = "Insert into catalogo (descripcion,material,color) values ('Camisa','lana','celeste');"
        val insertarcatalogo3 = "Insert into catalogo (descripcion,material,color) values ('Camisa','poliester','amarillo');"
        val insertarcatalogo4 = "Insert into catalogo (descripcion,material,color) values ('Pantalon','algodon','blanco');"
        val insertarcatalogo5 = "Insert into catalogo (descripcion,material,color) values ('Pantalon','poliester','azul');"

        val insertarCliente = "Insert into cliente (id_cliente,nombre,telefono) values ('1725158123','Ernesto Yaselga','0995193611');"
        val insertarCliente1 = "Insert into cliente (id_cliente,nombre,telefono) values ('1725158122','Otto Olga','0934593621');"


        val insertarPedido = "Insert into pedido (id_pedido,id_cliente,id_prenda,precio_total) values ('1','1725158123','3','4000');"

        baseDeDatos?.execSQL(crearTablaCliente)
        baseDeDatos?.execSQL(crearTablaPrenda)
        baseDeDatos?.execSQL(crearTablaPedido)
        //baseDeDatos?.execSQL(crearTablaDetalle)

        baseDeDatos?.execSQL(insertarCliente)
        baseDeDatos?.execSQL(insertarCliente1)
        baseDeDatos?.execSQL(insertarcatalogo1)
        baseDeDatos?.execSQL(insertarcatalogo2)
        baseDeDatos?.execSQL(insertarcatalogo3)
        baseDeDatos?.execSQL(insertarcatalogo4)
        baseDeDatos?.execSQL(insertarcatalogo5)
        baseDeDatos?.execSQL(insertarPedido)

    }

    override fun onUpgrade(baseDeDatos: SQLiteDatabase?,
                           antiguaVersion: Int,
                           nuevaVersion: Int) {
        baseDeDatos?.execSQL("detach database yasmoDB")

    }

    //CLIENTE

    fun getCliente(clientes: ArrayList<Cliente>){
        val statement = "select id_cliente, nombre, telefono from cliente;"
        val dbReadable = readableDatabase
        val resultado = dbReadable.rawQuery(statement, null)
        clientes.clear()

        if (resultado.moveToFirst()) {
            do {
                clientes.add(Cliente(resultado.getString(0), resultado.getString(1), resultado.getString((2))))
            } while (resultado.moveToNext())
        }

        resultado.close()
        dbReadable.close()
    }

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

    //PRENDA

    fun getPrenda(catalogo: ArrayList<Prenda>){
        val statement = "select id_prenda, descripcion, material, color from catalogo;"
        val dbReadable = readableDatabase
        val resultado = dbReadable.rawQuery(statement, null)
        catalogo.clear()

        if (resultado.moveToFirst()) {
            do {
                catalogo.add(Prenda(resultado.getString(0), resultado.getString(1), resultado.getString(2),resultado.getString(3)))
            } while (resultado.moveToNext())
        }

        resultado.close()
        dbReadable.close()
    }
    fun insertarPrenda(
                       descripcion: String,
                       material: String,
                       color: String): Boolean {
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos

        cv.put("descripcion", descripcion)
        cv.put("material", material)
        cv.put("color", color)

        val resultado: Long = dbWriteable
                .insert(
                        "catalogo", // Nombre de la tabla
                        null,
                        cv)

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true

    }

    fun actualizarPrenda(id:String,descripcion: String,material: String, color: String):Boolean{
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos

        cv.put("id_prenda", id)
        cv.put("descripcion", descripcion)
        cv.put("material", material)
        cv.put("color", color)

        val resultado = dbWriteable
                .update(
                        "catalogo", // Nombre de la tabla
                        cv, // Valores a actualizarse
                        "id_prenda=?", // Where
                        arrayOf(id) // Parametros
                )

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true
    }

    fun eliminarPrenda(id:String):Boolean{
        val dbWriteable = this.writableDatabase
        val parametros = arrayOf(id)
        val descripcionTabla = "prenda"
        val clausulaWhere = "id_prenda = ?"
        val respuesta = dbWriteable.delete(
                descripcionTabla,
                clausulaWhere,
                parametros
        )
        return  if(respuesta == -1 )false else true
    }

    //Pedido
    fun getPedido(pedidos: ArrayList<Pedido>){
        val statement = "select id_pedido, id_cliente, id_prenda, precio_total from pedido;"
        val dbReadable = readableDatabase
        val resultado = dbReadable.rawQuery(statement, null)
        pedidos.clear()

        if (resultado.moveToFirst()) {
            do {
                pedidos.add(Pedido(resultado.getString(0), resultado.getString(1), resultado.getString(2),resultado.getInt(3)))
            } while (resultado.moveToNext())
        }

        resultado.close()
        dbReadable.close()
    }
    fun insertarPedido(
            id_cliente: String,
            id_prenda: String,
            precio_total: String): Boolean {
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos

        cv.put("id_cliente", id_cliente)
        cv.put("id_prenda", id_prenda)
        cv.put("precio_total", precio_total)

        val resultado: Long = dbWriteable
                .insert(
                        "pedido", // Nombre de la tabla
                        null,
                        cv)

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true

    }

    fun actualizarPedido(id:String,id_cliente: String,id_prenda: String, precio_total: String):Boolean{
        // Base de datos de escritura
        val dbWriteable = writableDatabase
        val cv = ContentValues()

        // Valores de los campos

        cv.put("id_pedido", id)
        cv.put("id_cliente", id_cliente)
        cv.put("id_prenda", id_prenda)
        cv.put("precio_total", precio_total)

        val resultado = dbWriteable
                .update(
                        "pedido", // Nombre de la tabla
                        cv, // Valores a actualizarse
                        "id_pedido=?", // Where
                        arrayOf(id) // Parametros
                )

        dbWriteable.close()

        return if (resultado.toInt() == -1) false else true
    }

    fun eliminarPedido(id:String):Boolean{
        val dbWriteable = this.writableDatabase
        val parametros = arrayOf(id)
        val id_clienteTabla = "pedido"
        val clausulaWhere = "id_pedido = ?"
        val respuesta = dbWriteable.delete(
                id_clienteTabla,
                clausulaWhere,
                parametros
        )
        return  if(respuesta == -1 )false else true
    }
}