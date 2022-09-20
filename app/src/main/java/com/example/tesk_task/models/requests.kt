package com.example.tesk_task.models

import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

 fun getCategoris():MutableList<Category>{
    val gson= Gson()
    val list= mutableListOf<Category>()
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://testtask.sebbia.com/v1/news/categories")
        .get()
        .addHeader("Accept", "application/json")
        .build()
    val response = client.newCall(request).execute().body!!.string()
    val jsonArray= JSONObject(response).getJSONArray("list")
    for(i in 0 until jsonArray.length()){
        val jsonObject=jsonArray.getJSONObject(i)
        val category=gson.fromJson(jsonObject.toString(),Category::class.java)
        if(category!=null) list.add(category)
    }
    return list
}
fun getNews(id:Int,page:Int):MutableList<Ness>{
    val gson= Gson()
    val list= mutableListOf<Ness>()
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://testtask.sebbia.com/v1/news/categories/$id/news?page=$page")
        .get()
        .addHeader("Accept", "application/json")
        .build()
    val response = client.newCall(request).execute().body!!.string()
    val jsonArray= JSONObject(response).getJSONArray("list")
    for(i in 0 until jsonArray.length()){
        val jsonObject=jsonArray.getJSONObject(i)
        val category=gson.fromJson(jsonObject.toString(),Ness::class.java)
        if(category!=null) list.add(category)
    }
    return list
}
fun getNess(id :Int):Ness{
    val gson= Gson()
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("http://testtask.sebbia.com/v1/news/details?id=$id")
        .get()
        .addHeader("Accept", "application/json")
        .build()
    val response=client.newCall(request).execute().body!!.string()
    val jsonObject=JSONObject(response).getJSONObject("news")
    val ness=gson.fromJson(jsonObject.toString(),Ness::class.java)
    return ness
}