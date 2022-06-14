package com.example.feature_search_screen.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_screen_local_db")
data class MovieEntity(
    val description: String,
    @PrimaryKey val id: String,
    val image: String,
    val resultType: String,
    val title: String
)
