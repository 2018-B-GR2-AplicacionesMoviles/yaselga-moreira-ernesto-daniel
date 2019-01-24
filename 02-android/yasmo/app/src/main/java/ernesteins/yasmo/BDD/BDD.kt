package ernesteins.yasmo.BDD

import ernesteins.yasmo.Clientes.Cliente
import ernesteins.yasmo.Catalogo.Prenda
import ernesteins.yasmo.Pedidos.Pedido

class BDD{
    companion object {
        val Clientes = ArrayList<Cliente>()
        val Catalogo = ArrayList<Prenda>()
        val Pedidos = ArrayList<Pedido>()
    }
}