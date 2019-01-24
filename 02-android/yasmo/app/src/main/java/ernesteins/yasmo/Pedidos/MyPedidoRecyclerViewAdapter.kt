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
import kotlinx.android.synthetic.main.fragment_prenda.view.*


class MyPedidoRecyclerViewAdapter(
        private val mValues: List<Pedido>,
        private val mListener: OnListFragmentInteractionListener?,private val context: Context)
    : RecyclerView.Adapter<MyPedidoRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Pedido
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
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
        holder.mIdView.text = item.id_pedido
        holder.mIdClienteView.text = item.id_cliente
        holder.mIdPrendaView.text = item.id_prenda
        holder.mPrecioTotalView.text = item.precio_total.toString()
        holder.mBotonEditarView.setOnClickListener {
            val prenda_n = Pedido(item.id_pedido,item.id_cliente,item.id_prenda,item.precio_total)
            irActividadModificar(prenda_n)
        }
        holder.mBotonPedidoView.setOnClickListener {
            val prenda_n = Pedido(item.id_pedido,item.id_cliente,item.id_prenda,item.precio_total)
            //irActividadModificar(prenda_n)
        }
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    fun irActividadModificar(prenda:Pedido){
        val intentActividadIntent = Intent(
                context,
                EditarPedidoActivity::class.java
        )

        intentActividadIntent.putExtra("prenda",prenda)
        ContextCompat.startActivity(context, intentActividadIntent, null)
    }
    fun irActividadPedido(prenda:Pedido){
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
        val mIdClienteView: TextView = mView.prenda_textView_descripcion
        val mIdPrendaView: TextView = mView.prenda_textView_material
        val mPrecioTotalView: TextView = mView.prenda_textView_color
        val mBotonEditarView: Button = mView.prenda_button_editar
        val mBotonPedidoView: Button = mView.prenda_button_pedido

        override fun toString(): String {
            return super.toString() + " '" + mIdClienteView.text + "'"
        }
    }
}
