package com.example.feature_main_screen.data.remote.dto.coming_soon

data class ComingSoonMovieResponse(
    val errorMessage: String,
    val items: List<ComingSoonMovieDto>
)