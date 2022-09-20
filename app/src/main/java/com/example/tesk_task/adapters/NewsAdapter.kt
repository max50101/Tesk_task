package com.example.tesk_task.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tesk_task.R
import com.example.tesk_task.holders.CategoryHolder
import com.example.tesk_task.holders.NewsHolder
import com.example.tesk_task.models.Category
import com.example.tesk_task.models.Ness

class NewsAdapter(val mContext: Context, val mutableList: MutableList<Ness>):
    RecyclerView.Adapter<NewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val view: View = layoutInflater.inflate(R.layout.ness_elem, parent, false)
        return NewsHolder(mContext, view)

    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val task=mutableList.get(position)
        holder.bindTask(task,position)
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }
}