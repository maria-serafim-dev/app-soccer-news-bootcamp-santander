package com.example.soccernews.ui.news

import androidx.lifecycle.*
import com.example.soccernews.domain.News
import com.example.soccernews.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository): ViewModel(){

    private var _news = MutableLiveData<MutableList<News>>()
    val news: LiveData<MutableList<News>> = _news
    private val _state = MutableLiveData<State>()
    val state : LiveData<State> = _state
    private lateinit var _newsRoom : LiveData<List<News>>
    val newsRoom: LiveData<List<News>> get() = _newsRoom

    init {
        getNews()
        getNewsRooms()
    }

    fun getNews(){
        _state.value = State.DOING
        viewModelScope.launch{
            _news.value = repository.getNewsRemote()
            _state.value = State.DONE
        }
    }

    private fun getNewsRooms() {
        _newsRoom = repository.allFavoriteNews.asLiveData()
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