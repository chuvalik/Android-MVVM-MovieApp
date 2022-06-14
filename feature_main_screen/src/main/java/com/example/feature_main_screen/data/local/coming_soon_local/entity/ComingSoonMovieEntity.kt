package com.example.feature_main_screen.data.local.coming_soon_local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coming_soon_movie_local_db")
data class ComingSoonMovieEntity(
    @PrimaryKey
    val id: String,
    val image: String,
    val releaseState: String,
    val fullTitle: String,
    val year: String
)