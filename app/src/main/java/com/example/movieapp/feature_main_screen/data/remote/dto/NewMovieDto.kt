package com.example.movieapp.feature_main_screen.data.remote.dto

data class NewMovieDto(
    val genre: String,
    val id: Int,
    val picture: String,
    val rating: Double,
    val title: String
)