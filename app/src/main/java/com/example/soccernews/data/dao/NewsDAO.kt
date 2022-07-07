package com.example.soccernews.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soccernews.domain.News

@Dao
interface NewsDAO {

    @Query("SELECT * FROM news WHERE favorite = 1")
    fun loadFavoriteNews(): List<News>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg news: News)

}