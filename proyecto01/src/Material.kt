class Material(var nombre:String,
               var color:String,
               var metrica:String,
               var cantidad:Int,
               var precio:Double){

    fun getnombre() = "$nombre"
    fun getcolor() = "$color"
    fun getmetrica() = "$metrica"
    fun getcantidad(): Int {return cantidad}
    fun getprecio():Double {return precio}

    fun setnombre(vnombre:String){
        nombre = vnombre
    }
    fun setcolor(vcolor:String){
        color = vcolor
    }
    fun setmetrica(vmetrica:String){
        metrica = vmetrica
    }
    fun setcantidad(vcantidad:Int){
        cantidad = vcantidad
    }
    fun setprecio(vprecio:Double) {
        precio = vprecio
    }

    override fun toString(): String {
        return "\nMaterial: ${this.nombre}; cantidad: ${this.cantidad} ${this.metrica}; precio: ${this.precio}"
    }

    fun Material(vnombre:String,
                 vcolor:String,
                 vmetrica:String,
                 vcantidad:Int,
                 vprecio:Double){

        nombre = vnombre
        color = vcolor
        metrica=vmetrica
        cantidad=vcantidad
        precio=vprecio

        //println("Material ingresado: ${toString()}")

    }

}
