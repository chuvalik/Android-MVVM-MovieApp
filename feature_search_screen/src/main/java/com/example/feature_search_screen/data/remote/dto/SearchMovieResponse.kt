package com.example.feature_search_screen.data.remote.dto

data class SearchMovieResponse(
    val errorMessage: String,
    val expression: String,
    val results: List<MovieDto>,
    val searchType: String
)