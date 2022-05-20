package com.example.feature_search_screen.data.mapper

import com.example.feature_search_screen.data.remote.dto.MovieDto
import com.example.feature_search_screen.domain.model.MovieDomain

fun MovieDto.toMovieDomain(): MovieDomain {
    return MovieDomain(
        description = description,
        id = id,
        image = image,
        resultType = resultType,
        title = title
    )
}