package ernesteins.yasmo.Pedidos
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
import ernesteins.yasmo.Pedidos.PedidoFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_pedido.view.*

class MyPedidoRecyclerViewAdapter(
        private val mValues: List<Pedido>,
        private val mListener: OnListFragmentInteractionListener?,private val context: Context)
    : RecyclerView.Adapter<MyPedidoRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Pedido
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_pedido, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id_pedido
        holder.mIdClienteView.text = item.id_cliente
        holder.mIdPrendaView.text = item.id_prenda
        holder.mPrecioTotalView.text = item.precio_total.toString()
        holder.mBotonEditarView.setOnClickListener {
            val pedido_n = Pedido(item.id_pedido,item.id_cliente,item.id_prenda,item.precio_total)
            irActividadModificar(pedido_n)
        }
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    fun irActividadModificar(pedido:Pedido){
        val intentActividadIntent = Intent(
                context,
                EditarPedidoActivity::class.java
        )

        intentActividadIntent.putExtra("pedido",pedido)
        ContextCompat.startActivity(context, intentActividadIntent, null)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.pedido_textView_id_producto
        val mIdClienteView: TextView = mView.pedido_textView_id_cliente
        val mIdPrendaView: TextView = mView.pedido_textView_id_producto
        val mPrecioTotalView: TextView = mView.pedido_textView_preciototal
        val mBotonEditarView: Button = mView.pedido_button_editar

        override fun toString(): String {
            return super.toString() + " '" + mIdView.text + "'"
        }
    }
}
