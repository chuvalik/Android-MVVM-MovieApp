package com.example.feature_main_screen.data.remote.dto.trending

data class TrendingMovieResponse(
    val errorMessage: String,
    val items: List<TrendingMovieDto>
)