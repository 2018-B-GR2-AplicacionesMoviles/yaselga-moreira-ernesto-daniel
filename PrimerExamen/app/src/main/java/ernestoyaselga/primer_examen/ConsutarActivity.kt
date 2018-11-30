package ernestoyaselga.primer_examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_consutar.*

class ConsutarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consutar)

        var arreglocasas = ArrayList<Casa>()

        val casaDefault = Casa("Quito",
                "2 Puentes",
                "Necochea S7-520",
                "Tienda: De la esquina",
                12000,
                100.00)

        arreglocasas.add(casaDefault)

        val adaptadorCasa = ArrayAdapter<Casa>(this,
                android.R.layout.simple_list_item_1,
                arreglocasas
        )


        lista_casas.adapter = adaptadorCasa

        val context = this
        lista_casas.setOnItemClickListener { _, _, position, _ ->
            // 1
            //val selectedRecipe = recipeList[position]

            // 2
            //val detailIntent = RecipeDetailActivity.newIntent(context, selectedRecipe)

            // 3
            IrEditar()
        }



    }

    fun IrEditar(){
        val intentActiv = Intent(this,EditarActivity::class.java)
        startActivity(intentActiv)
    }

}
