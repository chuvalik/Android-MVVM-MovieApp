package com.example.feature_detail_screen.domain.model

data class MovieDetailsDomain(
    val actorList: List<ActorDomain>,
    val fullTitle: String,
    val genres: String,
    val id: String,
    val imDbRating: String,
    val image: String,
    val plot: String,
    val runtimeMins: String,
    val similars: List<SimilarDomain>,
)