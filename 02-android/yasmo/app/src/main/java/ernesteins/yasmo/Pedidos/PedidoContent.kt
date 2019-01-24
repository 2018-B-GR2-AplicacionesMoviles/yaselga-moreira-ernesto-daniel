import ernesteins.yasmo.BDD.BDD.Companion.Pedidos
import ernesteins.yasmo.Pedidos.Pedido
import java.util.HashMap

object PedidoContent{

    val ITEMS: MutableList<Pedido>

    val ITEM_MAP: MutableMap<String, Pedido> = HashMap()

    private val COUNT = 25

    init {
        ITEMS = Pedidos
    }

    private fun addItem(item: Pedido) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id_prenda, item)
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }
}
