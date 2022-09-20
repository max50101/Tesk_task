package com.example.tesk_task

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tesk_task.models.Ness
import com.example.tesk_task.models.getNess
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "ness_info"


/**
 * A simple [Fragment] subclass.
 * Use the [NessFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NessFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    lateinit var titleTextView:TextView
    lateinit var dateTextView:TextView
    lateinit var shortDescTextView:TextView
    lateinit var fullDescTextView:TextView
    lateinit var nessInfo: Ness
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_ness, container, false)
        init(view)
        putText()
        request()
        return view
    }
    fun init(view:View){
        titleTextView=view.findViewById(R.id.title)
        dateTextView=view.findViewById(R.id.date)
        shortDescTextView=view.findViewById(R.id.short_description)
        fullDescTextView=view.findViewById(R.id.full_desc)
        nessInfo= Gson().fromJson(param1,Ness::class.java)
    }
    fun putText(){
        titleTextView.text=nessInfo.title
        dateTextView.text=nessInfo.date
        shortDescTextView.text=nessInfo.shortDescription
    }
    fun request(){
        GlobalScope.launch(Dispatchers.IO){
            nessInfo= getNess(nessInfo.id)
            launch(Dispatchers.Main){
                fullDescTextView.text=if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(nessInfo.fullDescription, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(nessInfo.fullDescription)
                }
            }
        }
    }
    companion object {

        fun newInstance(param1: String) =
            NessFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}