package ernesteins.yasmo.Catalogo
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ernesteins.yasmo.R
import ernesteins.yasmo.Catalogo.PrendaFragment.OnListFragmentInteractionListener
import ernesteins.yasmo.Pedidos.EditarPedidoActivity
import kotlinx.android.synthetic.main.fragment_prenda.view.*

class MyPrendaRecyclerViewAdapter(
        private val mValues: List<Prenda>,
        private val mListener: OnListFragmentInteractionListener?,private val context: Context)
    : RecyclerView.Adapter<MyPrendaRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Prenda
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_prenda, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id_prenda
        holder.mDescripcionView.text = item.descripcion
        holder.mMaterialView.text = item.material
        holder.mColorView.text = item.color
        holder.mBotonEditarView.setOnClickListener {
            val prenda_n = Prenda(item.id_prenda,item.descripcion,item.material,item.color)
            irActividadModificar(prenda_n)
        }
        holder.mBotonPrendaView.setOnClickListener {
            val prenda_n = Prenda(item.id_prenda,item.descripcion,item.material,item.color)
            //irActividadModificar(prenda_n)
        }
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    fun irActividadModificar(prenda:Prenda){
       val intentActividadIntent = Intent(
               context,
               EditarPrendaActivity::class.java
       )

       intentActividadIntent.putExtra("prenda",prenda)
       ContextCompat.startActivity(context, intentActividadIntent, null)
    }
    fun irActividadPedido(prenda:Prenda){
        val intentActividadIntent = Intent(
                context,
                EditarPedidoActivity::class.java
        )

        intentActividadIntent.putExtra("prenda",prenda)
        ContextCompat.startActivity(context, intentActividadIntent, null)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.prenda_textView_id
        val mDescripcionView: TextView = mView.prenda_textView_descripcion
        val mMaterialView: TextView = mView.prenda_textView_material
        val mColorView: TextView = mView.prenda_textView_color
        val mBotonEditarView: Button = mView.prenda_button_editar
        val mBotonPrendaView: Button = mView.prenda_button_pedido

        override fun toString(): String {
            return super.toString() + " '" + mDescripcionView.text + "'"
        }
    }
}
