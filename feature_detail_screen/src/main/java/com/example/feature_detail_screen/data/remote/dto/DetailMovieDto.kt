package com.example.feature_detail_screen.data.remote.dto

data class DetailMovieDto(
    val cast: List<CastDto>,
    val genre: String,
    val id: Int,
    val overview: String,
    val poster: String,
    val rating: Double,
    val title: String
)