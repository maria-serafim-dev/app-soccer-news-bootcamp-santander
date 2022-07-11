package com.example.soccernews.data.remote

import com.example.soccernews.domain.News
import retrofit2.http.GET

interface NewsApi {

    @GET("news.json")
    suspend fun getNews() : MutableList<News>


}