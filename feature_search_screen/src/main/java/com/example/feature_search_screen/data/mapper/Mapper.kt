package com.example.feature_search_screen.data.mapper

import com.example.feature_search_screen.data.local.entity.MovieEntity
import com.example.feature_search_screen.data.remote.dto.MovieDto
import com.example.feature_search_screen.domain.model.MovieDomain

fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        description = description,
        id = id,
        image = image,
        resultType = resultType,
        title = title
    )
}

fun MovieEntity.toMovieDomain(): MovieDomain {
    return MovieDomain(
        description = description,
        id = id,
        image = image,
        resultType = resultType,
        title = title
    )
}




