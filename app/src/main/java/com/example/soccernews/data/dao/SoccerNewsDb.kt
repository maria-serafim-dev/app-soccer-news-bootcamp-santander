package com.example.soccernews.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.soccernews.domain.News

@Database(entities = [News::class], version = 1)
abstract class SoccerNewsDb : RoomDatabase() {
    abstract fun newsDao(): NewsDAO

    companion object {
        @Volatile
        private var INSTANCE: SoccerNewsDb? = null

        fun getDatabase(context: Context): SoccerNewsDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SoccerNewsDb::class.java,
                    "soccer-news-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
