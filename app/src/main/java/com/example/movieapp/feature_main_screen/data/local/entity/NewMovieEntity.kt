package com.example.movieapp.feature_main_screen.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "main_screen_db")
data class NewMovieEntity(
    val genre: String,
    @PrimaryKey val id: Int,
    val picture: String,
    val rating: Double,
    val title: String
)
