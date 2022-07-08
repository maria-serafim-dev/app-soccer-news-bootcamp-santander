package com.example.soccernews.ui.favorites

import androidx.lifecycle.*
import com.example.soccernews.domain.News
import com.example.soccernews.repository.NewsRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: NewsRepository) : ViewModel() {


    private lateinit var _news : LiveData<List<News>>
    val news: LiveData<List<News>> get() = _news

    init {
        getNews()
    }

    private fun getNews() {
        _news = repository.allFavoriteNews.asLiveData()
    }

    fun saveNews(news: News){
        viewModelScope.launch {
            repository.saveNewsFavorite(news)
        }
    }
}


class FavoriteNewsViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}