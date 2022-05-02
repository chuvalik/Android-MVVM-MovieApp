package com.example.movieapp.feature_main_screen.data.mapper

import com.example.movieapp.feature_main_screen.data.remote.dto.NewMovieDto
import com.example.movieapp.feature_main_screen.domain.model.NewMovieDomain
import com.example.movieapp.feature_main_screen.domain.model.NewMovieEntity

fun NewMovieDto.toNewMovieEntity(): NewMovieEntity {
    return NewMovieEntity(
        genre = genre,
        id = id,
        picture = picture,
        rating = rating,
        title = title
    )
}

fun NewMovieEntity.toNewMovieDomain(): NewMovieDomain {
    return NewMovieDomain(
        genre = genre,
        id = id,
        picture = picture,
        rating = rating,
        title = title
    )
}