package com.example.soccernews.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soccernews.domain.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDAO {

    @Query("SELECT * FROM news_table WHERE favorite = 1")
    fun loadFavoriteNews(): Flow<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(news: News)

}