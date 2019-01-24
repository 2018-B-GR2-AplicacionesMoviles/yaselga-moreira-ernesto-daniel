package yasmo.sastreria

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

import yasmo.sastreria.ClienteFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.clientes_layout.view.*
import yasmo.sastreria.R.layout.activity_clientes

/**
 * [RecyclerView.Adapter] that can display a [Cliente] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ClienteRecyclerView(
        private val mValues: List<Cliente>,
        private val mListener: ClienteFragment.OnListFragmentInteractionListener?, private val context: Context)
    : RecyclerView.Adapter<ClienteRecyclerView.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Cliente
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.clientes_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]

        holder.cedulaTextView.text = item.id_cliente
        holder.nombreTextView.text = item.nombre
        holder.telefonoTextView.text = item.telefono
        holder.cliente_imageButton?.setOnClickListener {
            val Cliente_s = Cliente(item.id_cliente,item.nombre,item.telefono)
            irActividadModificar(Cliente_s)
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
            Log.i("click-evento","$item");
        }
    }

    fun irActividadModificar(Cliente:Cliente){
        val intentActividadIntent = Intent(
                context,
                activity_clientes::class.java
        )

        intentActividadIntent.putExtra("Cliente",Cliente)
        startActivity(context,intentActividadIntent,null)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        //val mIdView: TextView = mView.item_number
        val nombreTextView:TextView = mView.cliente_textView_nombre
        val cedulaTextView:TextView = mView.cliente_textView_id
        val telefonoTextView:TextView = mView.cliente_textView_telefono
        val cliente_imageButton: ImageButton? = mView.cliente_imageButton


        override fun toString(): String {
            return super.toString() + " '" + nombreTextView.text + "'"
        }
    }
}



