package com.example.movieapp.feature_detail_screen.data.mapper

import com.example.movieapp.feature_detail_screen.data.remote.dto.CastDto
import com.example.movieapp.feature_detail_screen.data.remote.dto.DetailMovieDto
import com.example.movieapp.feature_detail_screen.domain.model.CastDomain
import com.example.movieapp.feature_detail_screen.domain.model.DetailMovieDomain

fun DetailMovieDto.toDetailMovieDomain(): DetailMovieDomain {
    return DetailMovieDomain(
        cast = cast.map { it.toCastDomain() },
        genre = genre,
        id = id.toString(),
        overview = overview,
        poster = poster,
        rating = rating.toString(),
        title = title
    )
}

fun CastDto.toCastDomain(): CastDomain {
    return CastDomain(
        characterName = characterName,
        id = id.toString(),
        name = name,
        picture = picture
    )
}