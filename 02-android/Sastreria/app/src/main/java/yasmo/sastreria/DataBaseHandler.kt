package yasmo.sastreria

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DatabaseHandler.DB_NAME, null, DatabaseHandler.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                ID + " INTEGER PRIMARY KEY," +
                NOMBRE + " TEXT,"+
                TELEFONO+ " TEXT;"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addClient(cliente: Persona): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID, cliente.cedula)
        values.put(NOMBRE, cliente.nombre)
        values.put(TELEFONO, cliente.telefono)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }
    fun getCliente(_id: String): Persona{
        var id:String =""
        var nombre:String =""
        var telefono:String =""
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                id      = cursor.getString(cursor.getColumnIndex(ID))
                nombre  = cursor.getString(cursor.getColumnIndex(NOMBRE))
                telefono= cursor.getString(cursor.getColumnIndex(TELEFONO))
            }
        }
        val cliente = Persona(id,nombre,telefono)
        cursor.close()
        return cliente
    }
    val Cliente: List<Persona>
        get() {
            val taskList = ArrayList<Persona>()
            val db = writableDatabase
            val selectQuery = "SELECT  * FROM $TABLE_NAME"
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor != null) {
                cursor.moveToFirst()
                while (cursor.moveToNext()) {

                    var id = cursor.getString(cursor.getColumnIndex(ID))
                    var name = cursor.getString(cursor.getColumnIndex(NOMBRE))
                    var desc = cursor.getString(cursor.getColumnIndex(TELEFONO))
                    var clientes = Persona(id,name,desc)
                    taskList.add(clientes)
                }
            }
            cursor.close()
            return taskList
        }

    fun updateCliente(clientes: Persona): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NOMBRE, clientes.nombre)
        values.put(TELEFONO, clientes.telefono)
        val _success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(clientes.cedula.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    fun deleteCliente(_id: String): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "Sasteria"
        private val TABLE_NAME = "Clientes"
        private val ID = "Id"
        private val NOMBRE = "Nombre"
        private val TELEFONO = "Telefono"
    }
}






