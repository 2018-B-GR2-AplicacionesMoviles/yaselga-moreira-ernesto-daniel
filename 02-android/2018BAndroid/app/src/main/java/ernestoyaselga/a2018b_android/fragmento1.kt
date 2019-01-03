package ernestoyaselga.a2018b_android

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_fragmento1.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [fragmento1.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [fragmento1.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class fragmento1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(
                R.layout.fragment_fragmento1, // XML A USARSE
                container,
                false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {

            Log.i("fragmentos", arguments!!.getString("nombre"))

            text_view_fragmento
                    .text = arguments!!.getString("nombre")

        }
    }
}
