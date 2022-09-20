package com.example.tesk_task.holders

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tesk_task.NessFragment
import com.example.tesk_task.NewsFragment
import com.example.tesk_task.R
import com.example.tesk_task.models.Category
import com.example.tesk_task.models.Ness
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class NewsHolder (val context: Context, val itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    lateinit var mNess: Ness
    lateinit var mTitle: TextView
    lateinit var mDate:TextView
    lateinit var mShortDescription:TextView
    var mposition=0
    override fun onClick(p0: View?) {
        (context as FragmentActivity).supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, NessFragment.newInstance(Gson().toJson(mNess)))
                .addToBackStack("tag")
            commit()
        }


    }
    init{
        itemView.setOnClickListener(this)
        mTitle=itemView.findViewById(R.id.title)
        mDate=itemView.findViewById(R.id.date)
        mShortDescription=itemView.findViewById(R.id.short_description)

    }
    fun bindTask(ness: Ness, position:Int){
        mNess=ness
        val simpleDateFormat= SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.sss'Z'", Locale.getDefault())
        simpleDateFormat.timeZone= TimeZone.getTimeZone("GMT")
        val resultDate= SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(simpleDateFormat.parse(ness.date))
        mTitle.text=ness.title
        mTitle.setTypeface(Typeface.DEFAULT_BOLD)
        mDate.text=resultDate
        mShortDescription.text=ness.shortDescription
       // mNess.date=resultDate
    }


}
