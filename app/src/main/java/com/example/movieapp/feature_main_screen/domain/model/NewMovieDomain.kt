package com.example.movieapp.feature_main_screen.domain.model

data class NewMovieDomain(
    val genre: String,
    val id: Int,
    val picture: String,
    val rating: Double,
    val title: String
)
