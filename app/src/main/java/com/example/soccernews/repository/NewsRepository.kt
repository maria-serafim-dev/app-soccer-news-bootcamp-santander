package com.example.soccernews.repository

import androidx.annotation.WorkerThread
import com.example.soccernews.data.dao.NewsDAO
import com.example.soccernews.domain.News
import kotlinx.coroutines.flow.Flow

class NewsRepository(private val newsDao: NewsDAO){

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun saveNewsFavorite(news: News) {
        newsDao.save(news)
    }

    val allFavoriteNews : Flow<List<News>> = newsDao.loadFavoriteNews()

}