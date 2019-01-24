package ernesteins.yasmo.Clientes

import ernesteins.yasmo.BDD.BDD.Companion.Clientes
import java.util.HashMap

object ClienteContent {

    val ITEMS: MutableList<Cliente>

    val ITEM_MAP: MutableMap<String, Cliente> = HashMap()

    private val COUNT = 25

    init {

        ITEMS = Clientes
    }
    private fun addItem(item: Cliente) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id_cliente, item)
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore categoria_id information here.")
        }
        return builder.toString()
    }
}
