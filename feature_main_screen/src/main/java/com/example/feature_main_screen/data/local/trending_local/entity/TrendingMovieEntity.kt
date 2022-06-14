package com.example.feature_main_screen.data.local.trending_local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending_movie_local_db")
data class TrendingMovieEntity(
    val fullTitle: String,
    @PrimaryKey
    val id: String,
    val imDbRating: String,
    val image: String
)