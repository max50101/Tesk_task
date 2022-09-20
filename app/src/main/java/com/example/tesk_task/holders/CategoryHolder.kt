package com.example.tesk_task.holders

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tesk_task.NewsFragment
import com.example.tesk_task.R
import com.example.tesk_task.models.Category
import com.google.android.material.imageview.ShapeableImageView
import com.google.gson.Gson

class CategoryHolder (val context: Context, val itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    lateinit var mCategory: Category
    lateinit var mName: TextView
    var id=0
    var mposition=0
    override fun onClick(p0: View?) {
        (context as FragmentActivity).supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,NewsFragment.newInstance(id.toString()))
                .addToBackStack("tag")
            commit()
        }


    }
    init{
        itemView.setOnClickListener(this)
        mName=itemView.findViewById(R.id.category_name)

    }
    fun bindTask(category: Category, position:Int){
        if(category!=null&&category.name!=null&&category.id!=null){
            mName.text=category.name
            id=category.id
        }
    }


}
