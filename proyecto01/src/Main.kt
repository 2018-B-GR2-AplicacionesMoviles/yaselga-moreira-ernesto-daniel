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

    var opc = 99
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
    var opc = 99
    while (opc != 0){
        println("1.- Ingresar nuevo material")
        println("2.- Ingresar nueva prenda")
        println("0.- Volver")
        opc = reader.nextInt()

        when (opc){
            1 -> quemar_materiales()
            2 -> quemar_prendas()
            0 -> println("saliendo de menu ingresar...")
        }
    }
}

fun submenuConsultar(){
    var opc = 99
    while (opc != 0){
        println("1.- Consultar materiales")
        println("2.- Consultar prendas")
        println("0.- Volver")
        opc = reader.nextInt()

        when (opc){
            1 -> imprimir_materiales()
            2 -> imprimir_prendas()
            0 -> println("saliendo de menu consultar...")
        }
    }
}

fun submenuEditar(){
    var opc = 99
    while (opc != 0){
        println("1.- Editar material")
        println("2.- Editar prenda")
        println("0.- Volver")
        opc = reader.nextInt()

        when (opc){
            1 -> {imprimir_materiales()
                editar_material()}
            2 -> {imprimir_prendas()
                editar_prenda()}
            0 -> println("saliendo de menu editar...")
        }
    }
}



fun submenuBorrar(){
    var opc = 99
    while (opc != 0){
        println("1.- Borrar material (todos)")
        println("2.- Borrar prenda (todos)")
        println("3.- Borrar material")
        println("4.- Borrar prenda")
        println("0.- Volver")
        opc = reader.nextInt()

        when (opc){
            1 -> materiales.removeAll(materiales)
            2 -> prendas.removeAll(prendas)
            3 -> {imprimir_materiales()
                borrar_material()}
            4 -> {imprimir_prendas()
                borrar_prenda()}
            0 -> println("saliendo de menu borrar...")
        }
    }
}


fun borrar_material(){
    print("Ingrese el indice del material a borrar")
    var indice = readLine().toString().toInt()
    materiales.removeAt(indice)
    println("Material borrado con exito")
}

fun borrar_prenda(){
    print("Ingrese el indice del material a borrar")
    var indice = readLine().toString().toInt()
    prendas.removeAt(indice)
    println("Prenda borrada con exito")
}

fun editar_prenda(){
    println("Ingresar el inidice de la prenda a editar")
    var indice = readLine()!!
    println(prendas[indice.toInt()])
    println("Ingrese el nombre: ")
    prendas[indice.toInt()].setnombre(readLine().toString())
    println("Escoja el material ${materiales.toString()}")
    prendas[indice.toInt()].setmaterial(materiales[readLine().toString().toInt()])
    println("Ingrese el precio: ")
    prendas[indice.toInt()].setprecio(readLine().toString().toDouble())
    println("Ingrese el tipo: ")
    prendas[indice.toInt()].settipo(readLine().toString())
    println("Ingrese la descripcion: ")
    prendas[indice.toInt()].setdescripcion(readLine().toString())
}
fun editar_material(){
    println("Ingresar el inidice de la prenda a editar")
    var indice = readLine()!!
    println(materiales[indice.toInt()])
    println("Ingrese el nombre: ")
    materiales[indice.toInt()].setnombre(readLine().toString())
    println("Ingrese el color: ")
    materiales[indice.toInt()].setcolor(readLine().toString())
    println("Ingrese el precio: ")
    materiales[indice.toInt()].setprecio(readLine().toString().toDouble())
    println("Ingrese la cantidad: ")
    materiales[indice.toInt()].setcantidad(readLine().toString().toInt())
}

fun quemar_prendas(){
    prendas.add(Prenda("pantalon","inferior","pantalon formal",50.00,materiales[0]))
    prendas.add(Prenda("camisa","superior","camisa formal",30.00,materiales[0]))
    prendas.add(Prenda("abrigo","superior","abrigo de lana",46.00,materiales[0]))
    prendas.add(Prenda("chaleco","superior","chaleco comodo",25.00,materiales[0]))
}
fun quemar_materiales(){
    materiales.add(Material("seda","azul oscuro","metros",10, 5.0))
    materiales.add(Material("lino","blanco","metros",43, 7.0))
    materiales.add(Material("lana","blanco","metros",22, 2.5))
    materiales.add(Material("cuero","negro","metros",15, 3.5))
}

fun imprimir_materiales(){
    var n = 0
    for (item in materiales) {
        println("${n}.- ${item}")
        n++
    }
}

fun imprimir_prendas(){
    var n = 0
    for (item in prendas) {
        println("${n}.- ${item}")
        n++
    }
}