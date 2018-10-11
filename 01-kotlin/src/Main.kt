
import java.util.Date

fun main(args: Array<String>) {
    println("Hello, world!!!")

    //int edad = 29;
    //Mutar -> puede cambiarse /reasignar
    var edad: Int = 29
    edad = 10

    //inmutable -> no se puede cambiar
    val edadInmutable:Int = 29

    //Duck Typing
    var curso = 291 //-> kotlin asume que es entero 

    var Nombre = "Ernesto" //-> kotlin asume que es string

    var Apellido = 'a' //-> kotlin asume que es char 

    var casado = true //-> kotlin asume que es booleano

    var sueldo = 10.2 //-> kotlin asume que es decimal 

    var fechaNacimiento = Date()

    println(fechaNacimiento.toString())

    when(casado){
        false -> println("Feliz")
        true -> println("Triste")
        else -> {
            println("no me voy a ejecutar")
            println("yo menos")
        }
    }

    println("Hola soy ${Nombre}") //string template, dentro de las llaves se puede escribir codigo java

    var bono = if (casado) 1000.00 else 0.0
    println("bono : "+bono)

    val sueldoTotal = calcularSueldo(bono)
    println("sueldo total = " + sueldoTotal)

    saludar()

    val adrian = Usuario("Ernesto")
    println(adrian)


}

fun calcularSueldo (bono:Double):Double{
    var sueldo = 800.00

    return sueldo + bono

}

fun saludar ():Unit{ //void = Unit -> funcion que no retorna nada (se puede omitir)

    println("Holaa ^_^")

}


class Usuario(public var nombre:String){
    /*public val nombre:String

    constructor(vnombre:String){
        nombre = vnombre
    }*/

    override fun toString(): String{
        return "Hola ${this.nombre}"
    }
}
