package yasmo.sastreria

import yasmo.sastreria.BDD.Companion.Clientes
import java.util.HashMap

/**
 * Helper class for providing sample nombre for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */

object ClienteContent {

    /**
     * An array of sample (dummy) items.
     */

    var ITEMS: MutableList<Cliente>

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, Cliente> = HashMap()

    private val COUNT = 25

    init {
        ITEMS = Clientes
        // Add some sample items.

        /*for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }*/
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

    /**
     * A dummy item representing a piece of nombre.
     */
    /*data class Cliente(val id_cliente: String, val nombre: String, val telefono: String) {
        override fun toString(): String = nombre
    }*/
}