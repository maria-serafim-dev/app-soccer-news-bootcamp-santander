package com.example.soccernews.ui.news

import androidx.lifecycle.*
import com.example.soccernews.data.remote.NewsApi
import com.example.soccernews.domain.News
import com.example.soccernews.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModel(private val repository: NewsRepository): ViewModel(){

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news
    private lateinit var newsApi: NewsApi
    private val _state = MutableLiveData<State>()
    val state : LiveData<State> = _state
    init {
        setupHttpClient()
        getNews()
    }

    private fun setupHttpClient() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://maria-serafim-dev.github.io/soccer-news-bootcamp-santander-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsApi =  retrofit.create(NewsApi::class.java)
    }

    fun getNews(){
        _state.value = State.DOING
        newsApi.getNews().enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if(response.isSuccessful){
                    _news.value = response.body()
                    _state.value = State.DONE
                }else{
                    _state.value = State.ERROR
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                t.printStackTrace()
                _state.value = State.ERROR
            }
        })
    }

    fun saveNews(news: News){
        viewModelScope.launch {
            repository.saveNewsFavorite(news)
        }
    }
}

class NewsViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

enum class State{
    DOING, DONE, ERROR
}