package com.example.soccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soccernews.data.NewsApi
import com.example.soccernews.domain.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModel : ViewModel() {

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news
    private lateinit var newsApi: NewsApi

    private fun setupHttpClient() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://maria-serafim-dev.github.io/soccer-news-bootcamp-santander-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsApi =  retrofit.create(NewsApi::class.java)
    }

    private fun getNews(){
        newsApi.getNews().enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if(response.isSuccessful){
                    _news.value = response.body()
                }else{

                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
            }

        })
    }
    init {
        setupHttpClient()
        getNews()
    }
}