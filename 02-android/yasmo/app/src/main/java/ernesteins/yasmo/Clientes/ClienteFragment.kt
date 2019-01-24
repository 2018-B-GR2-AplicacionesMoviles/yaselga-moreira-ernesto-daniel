package ernesteins.yasmo.Clientes

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ernesteins.yasmo.R
import ernesteins.yasmo.BDD.BDD.Companion.Clientes
import ernesteins.yasmo.BDD.SqliteHelper
//import ernesteins.yasmo.Clientes.ClienteContent.Cliente_S
/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ClienteFragment.OnListFragmentInteractionListener] interface.
 */
class ClienteFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val helper = SqliteHelper(context)
        helper.getCliente(Clientes)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cliente_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyClienteRecyclerViewAdapter(ClienteContent.ITEMS, listener, this.context)
            }
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Cliente?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                ClienteFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
