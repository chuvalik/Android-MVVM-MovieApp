package com.example.feature_detail_screen.domain.model

data class DetailMovieDomain(
    val cast: List<CastDomain>,
    val genre: String,
    val id: String,
    val overview: String,
    val poster: String,
    val rating: String,
    val title: String
)