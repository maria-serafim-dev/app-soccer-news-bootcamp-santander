package com.example.soccernews.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.soccernews.domain.News

@Database(entities = [News::class], version = 1)
abstract class SoccerNewsDb : RoomDatabase() {
    abstract fun newsDao(): NewsDAO
}
