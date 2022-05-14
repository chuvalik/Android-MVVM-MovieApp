package com.example.feature_main_screen.data.mapper

import com.example.feature_main_screen.data.remote.dto.NewMovieDto
import com.example.feature_main_screen.domain.model.NewMovieDomain

fun NewMovieDto.toNewMovieDomain(): NewMovieDomain {
    return NewMovieDomain(
        genre = genre,
        id = id.toString(),
        picture = picture,
        rating = rating.toString(),
        title = title
    )
}
