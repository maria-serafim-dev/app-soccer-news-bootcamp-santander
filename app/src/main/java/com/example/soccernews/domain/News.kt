package com.example.soccernews.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class News(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val link: String,
    var favorite: Boolean
)