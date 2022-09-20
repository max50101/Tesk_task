package com.example.tesk_task.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tesk_task.R
import com.example.tesk_task.holders.CategoryHolder
import com.example.tesk_task.models.Category

class CategoryAdapter (val mContext: Context, val mutableList: MutableList<Category>):
    RecyclerView.Adapter<CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val view: View = layoutInflater.inflate(R.layout.list_category_elem, parent, false)
        return CategoryHolder(mContext, view)

    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val task=mutableList.get(position)
        holder.bindTask(task,position)
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }
}