package com.example.feature_main_screen.data.remote.dto.coming_soon

data class ComingSoonMovieDto(
    val contentRating: String,
    val directorList: List<DirectorDto>,
    val directors: String,
    val fullTitle: String,
    val genreList: List<GenreDto>,
    val genres: String,
    val id: String,
    val imDbRating: String,
    val imDbRatingCount: String,
    val image: String,
    val metacriticRating: String,
    val plot: String,
    val releaseState: String,
    val runtimeMins: String,
    val runtimeStr: String,
    val starList: List<StarDto>,
    val stars: String,
    val title: String,
    val year: String
)