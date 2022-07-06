package com.example.soccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soccernews.domain.News

class NewsViewModel : ViewModel() {

    private val _news = MutableLiveData<List<News>>()
    private var listNews = mutableListOf<News>()
    val news: LiveData<List<News>> = _news

    init{
        listNews.add(News("Ferroviaria tem desfalque importante", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", "https://www.lance.com.br/files/article_main/uploads/2022/04/16/625b1204e908e.jpeg"))
        listNews.add(News("Ferrinha Joga no Sábado", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", "https://www.lance.com.br/files/article_main/uploads/2022/04/16/625b1204e908e.jpeg"))
        listNews.add(News("Copa do mundo Feminina inicia ", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", "https://www.lance.com.br/files/article_main/uploads/2022/04/16/625b1204e908e.jpeg"))

        _news.value = listNews
    }
}