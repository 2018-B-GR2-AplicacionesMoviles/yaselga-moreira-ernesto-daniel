package ernesteins.yasmo.Catalogo

import ernesteins.yasmo.Catalogo.Prenda
import ernesteins.yasmo.BDD.BDD.Companion.Catalogo
import java.util.HashMap

object PrendaContent {

    val ITEMS: MutableList<Prenda>

    val ITEM_MAP: MutableMap<String, Prenda> = HashMap()

    private val COUNT = 25

    init {
        ITEMS = Catalogo
    }

    private fun addItem(item: Prenda) {
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
