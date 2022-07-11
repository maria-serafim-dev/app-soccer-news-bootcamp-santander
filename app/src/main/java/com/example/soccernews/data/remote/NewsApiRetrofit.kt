package com.example.soccernews.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://maria-serafim-dev.github.io/soccer-news-bootcamp-santander-api/"

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object NewsApiRetrofit {
    val retrofitService : NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}