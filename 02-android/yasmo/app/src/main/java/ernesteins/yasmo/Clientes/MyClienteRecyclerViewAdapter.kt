package ernesteins.yasmo.Clientes

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
import ernesteins.yasmo.Clientes.ClienteFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_cliente.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyClienteRecyclerViewAdapter(
        private val mValues: List<Cliente>,
        private val mListener: OnListFragmentInteractionListener?,private val context: Context)
    : RecyclerView.Adapter<MyClienteRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Cliente
            // Notify the active callbacks interface (the activity, if ernesteins.yasmo.Clientesthe fragment is attached to
            // one) that an item has been selected.ernesteins.yasmo.Clientes
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_cliente, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id_cliente
        holder.mNombreView.text = item.nombre
        holder.mTelefonoView.text = item.telefono
        holder.mBotonView.setOnClickListener {
            val cliente_n = Cliente(item.id_cliente,item.nombre,item.telefono)
            irActividadModificar(cliente_n)
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    fun irActividadModificar(cliente:Cliente){
        val intentActividadIntent = Intent(
                context,
                EditarClienteActivity::class.java
        )

        intentActividadIntent.putExtra("cliente",cliente)
        ContextCompat.startActivity(context, intentActividadIntent, null)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.cliente_textView_id
        val mNombreView : TextView = mView.cliente_textView_nombre
        val mTelefonoView: TextView = mView.cliente_textView_telefono
        val mBotonView: Button = mView.cliente_button_modificar

        override fun toString(): String {
            return super.toString() + " '" + mNombreView.text + "'"
        }
    }
}
