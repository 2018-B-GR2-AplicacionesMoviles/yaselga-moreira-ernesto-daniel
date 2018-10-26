import java.util.Scanner

val reader = Scanner(System.`in`)
val materiales: MutableList<Material> = mutableListOf()
val prendas: MutableList<Prenda> = mutableListOf()


fun main(args: Array<String>) {
    println("S.A.STRE")
    menu()
    //println(prendas.toString())


}

fun menu(){

    var opc:Int = 99
    while(opc != 0){

    println("Hola Bienvenido a S.A.Stre")
    println("Elija una de las acciones para continuar:")
    println("1. Ingresar")
    println("2. Consultar")
    println("3. Editar")
    println("4. Borrar")
    println("0. Salir")

    opc = reader.nextInt()

        when (opc){
        1 -> submenuIngresar()
        2 -> submenuConsultar()
        3 -> submenuEditar()
        4 -> submenuBorrar()
        0 -> println("Hasta luego...")
        }
    }
}

fun submenuIngresar(){
    var opc:Int = 99
    while (opc != 0){
        println("1.- Ingresar nuevo material")
        println("2.- Ingresar nueva prenda")
        println("0.- Volver")
        opc = reader.nextInt()

        when (opc){
            1 -> materiales.add(Material("lana","azul","metros",10, 3.5))
            2 -> prendas.add(Prenda("pantalon","inferior","pantalon formal",50.00,materiales[0]))
            0 -> println("saliendo de menu ingresar...")
        }
    }
}

fun submenuConsultar(){
    var opc:Int = 99
    while (opc != 0){
        println("1.- Consultar materiales")
        println("2.- Consultar prendas")
        println("0.- Volver")
        opc = reader.nextInt()

        when (opc){
            1 -> println("Materiales: ${materiales.toString()}")
            2 -> println("Prendas: ${prendas.toString()}")
            0 -> println("saliendo de menu consultar...")
        }
    }
}

fun submenuEditar(){
    var opc:Int = 99
    while (opc != 0){
        println("1.- Editar material")
        println("2.- Editar prenda")
        println("0.- Volver")
        opc = reader.nextInt()

        when (opc){
            //1 -> materiales.add(Material("lana","azul","metros",10, 3.5))
            //2 -> prendas.add(Prenda("pantalon","inferior","pantalon formal",50.00,materiales[0]))
            0 -> println("saliendo de menu ingresar...")
        }
    }
}



fun submenuBorrar(){
    var opc:Int = 99
    while (opc != 0){
        println("1.- Borrar material")
        println("2.- Borrar prenda")
        println("0.- Volver")
        opc = reader.nextInt()

        when (opc){
        //    1 -> materiales.add(Material("lana","azul","metros",10, 3.5))
        //    2 -> prendas.add(Prenda("pantalon","inferior","pantalon formal",50.00,materiales[0]))
            0 -> println("saliendo de menu ingresar...")
        }
    }
}