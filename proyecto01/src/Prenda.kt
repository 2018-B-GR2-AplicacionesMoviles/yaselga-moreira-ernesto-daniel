class Prenda(var nombre:String,
             var tipo:String,
             var descripcion:String,
             var precio:Double,
             var material:Material){

    fun getnombre() = "$nombre"
    fun gettipo() = "$tipo"
    fun getdescripcion() = "$descripcion"
    fun getprecio() = "$precio"
    fun getmaterial() = "$material"

    fun setnombre(vnombre:String){
        nombre = vnombre
    }
    fun settipo(vtipo:String){
        tipo= vtipo
    }
    fun setdescripcion(vdescripcion:String){
        descripcion= vdescripcion
    }
    fun setprecio(vprecio:Double){
        precio = vprecio
    }
    fun setmaterial(vmaterial:Material){
        material = vmaterial
    }

    override fun toString(): String{
        return "\n Prenda: ${this.nombre}; precio: ${this.precio}; [${this.material.getnombre()}; [${this.material.getcolor()}]; descripcion: ${this.descripcion}"
    }

    fun Prenda(vnombre:String,
               vtipo:String,
               vdescripcion: String,
               vprecio: Double,
               vmaterial: Material){

        nombre=vnombre
        tipo=vtipo
        descripcion=vdescripcion
        precio=vprecio
        material=vmaterial

        //println("Prenda ingresada: ${toString()}")
    }
}