package com.example.feature_main_screen.data.remote.dto.new_movie

import com.example.feature_core.ui.DisplayableItem

data class TrendingMovieDto(
    val crew: String,
    val fullTitle: String,
    val id: String,
    val imDbRating: String,
    val imDbRatingCount: String,
    val image: String,
    val rank: String,
    val rankUpDown: String,
    val title: String,
    val year: String
)