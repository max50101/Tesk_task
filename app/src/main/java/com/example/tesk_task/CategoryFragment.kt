package com.example.tesk_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tesk_task.adapters.CategoryAdapter
import com.example.tesk_task.models.Category
import com.example.tesk_task.models.getCategoris
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var categoryListView: RecyclerView
    var categoryList= mutableListOf<Category>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_category, container, false)
        init(view)
        startRequest()
        return view
    }
    fun init(view:View)
    {
        categoryListView=view.findViewById(R.id.category_list)
        categoryListView.layoutManager= LinearLayoutManager(requireContext())
    }
    fun startRequest(){
        GlobalScope.launch(Dispatchers.IO) {
            categoryList= getCategoris()
            launch(Dispatchers.Main) {
                val catAdapter= CategoryAdapter(requireContext(),categoryList)
                categoryListView.adapter=catAdapter
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CategoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            CategoryFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}