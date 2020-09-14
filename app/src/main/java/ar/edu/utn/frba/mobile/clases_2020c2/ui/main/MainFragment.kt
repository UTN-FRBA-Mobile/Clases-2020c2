package ar.edu.utn.frba.mobile.clases_2020c2.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ar.edu.utn.frba.mobile.clases_2020c2.R
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MainFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var tweetsAdapter: TweetsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onStart() {
        super.onStart()

        list.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()) // Para parsear automágicamente el json
            .baseUrl("https://demo1958294.mockable.io/")
            .build()
            .create(TweetsService::class.java) // la interfaz que diseñaron antes

        service.getTweets().enqueue(object: Callback<GetTweetsResponse> {
            override fun onResponse(call: Call<GetTweetsResponse>, response: Response<GetTweetsResponse>) {
                tweetsAdapter = TweetsAdapter(listener, response.body()!!.tweets)
                list.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = tweetsAdapter
                }

                list.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
            override fun onFailure(call: Call<GetTweetsResponse>, error: Throwable) {
                Toast.makeText(activity, "No tweets founds!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun showFragment(fragment: Fragment)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment MainFragment.
         */
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}