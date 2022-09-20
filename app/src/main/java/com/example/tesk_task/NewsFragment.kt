package com.example.tesk_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tesk_task.adapters.CategoryAdapter
import com.example.tesk_task.adapters.NewsAdapter
import com.example.tesk_task.models.Category
import com.example.tesk_task.models.Ness
import com.example.tesk_task.models.getCategoris
import com.example.tesk_task.models.getNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "category_id"


/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    lateinit var newsListView: RecyclerView
    var newsList= mutableListOf<Ness>()
    var currentPage=0
     var categoryId=-1
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
        val view= inflater.inflate(R.layout.fragment_news, container, false)
        init(view)
        currentPage=0
        categoryId=param1!!.toInt()
        if(categoryId!=-1) {
            startRequest(categoryId, currentPage)
        }
        return view
    }
    fun init(view:View)
    {
        newsListView=view.findViewById(R.id.category_news)
        newsListView.layoutManager= LinearLayoutManager(requireContext())
        newsListView.addOnScrollListener(object:RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(!recyclerView.canScrollVertically(1)&&dy>0){
                    GlobalScope.launch(Dispatchers.IO) {
                        newsList.addAll(getNews(categoryId,currentPage))
                        launch(Dispatchers.Main) {
                            newsListView.adapter!!.notifyDataSetChanged()
                            currentPage++
                        }
                    }
                }
            }
        })
    }
    fun startRequest(current_id:Int,current_page:Int){
        GlobalScope.launch(Dispatchers.IO) {
            newsList= getNews(current_id,current_page)
            launch(Dispatchers.Main) {
                val catAdapter= NewsAdapter(requireContext(),newsList)
                newsListView.adapter=catAdapter
                currentPage++
            }
        }

    }

    override fun onResume() {
        super.onResume()
        currentPage=0
    }

    companion object {

        fun newInstance(param1: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)

                }
            }
    }
}