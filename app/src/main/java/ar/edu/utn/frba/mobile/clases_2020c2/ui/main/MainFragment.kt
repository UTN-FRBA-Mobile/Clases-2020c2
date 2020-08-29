package ar.edu.utn.frba.mobile.clases_2020c2.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ar.edu.utn.frba.mobile.clases_2020c2.R
import kotlinx.android.synthetic.main.main_fragment.*

private const val MAIN_FRAGMENT_ARG_LABEL = "MAIN_FRAGMENT_ARG_LABEL"

class MainFragment : Fragment() {
    private var label: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance(title: String) = MainFragment().apply {
            arguments = Bundle().apply {
                putString(MAIN_FRAGMENT_ARG_LABEL, title)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            label = it.getString(MAIN_FRAGMENT_ARG_LABEL)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.textView).text = label
        button.setOnClickListener {
            onButtonPressed()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.resources = MainViewModelResources(context!!)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun onButtonPressed() {
        listener?.onButtonTapped(viewModel.salute)
    }

    interface OnFragmentInteractionListener {
        fun onButtonTapped(salute: String)
    }
}