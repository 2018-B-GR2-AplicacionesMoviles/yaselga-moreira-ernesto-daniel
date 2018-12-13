package ernestoyaselga.primer_examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_consutar.*

class ConsutarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consutar)

        val adaptadorCasa = ArrayAdapter<Casa>(
            this, android.R.layout.simple_expandable_list_item_1, BDD.Casa)

        list_casa.adapter = adaptadorCasa
        val intentEditar = Intent(this, EditarActivity::class.java)

        list_casa
            .onItemClickListener =
                object : AdapterView.OnItemClickListener{
                    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val casa = BDD.Casa[position]
                        val pos = position
                        intentEditar.putExtra("casa",casa)
                        intentEditar.putExtra("pos", pos)
                        startActivity(intentEditar)

                    }
                }
    }
}
