package com.example.soccernews.data

import com.example.soccernews.domain.News
import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {

    @GET("news.json")
    fun getNews() : Call<List<News>>


}