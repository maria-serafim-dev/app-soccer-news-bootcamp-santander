package com.example.soccernews

import android.app.Application
import com.example.soccernews.data.dao.SoccerNewsDb
import com.example.soccernews.repository.NewsRepository

class NewsApplication : Application() {

    private val database by lazy { SoccerNewsDb.getDatabase(this) }
    val repository by lazy { NewsRepository(database.newsDao()) }

}