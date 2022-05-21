package com.example.feature_main_screen.data.mapper

import com.example.feature_main_screen.data.remote.dto.coming_soon.ComingSoonMovieDto
import com.example.feature_main_screen.data.remote.dto.new_movie.TrendingMovieDto
import com.example.feature_main_screen.domain.model.ComingSoonMovieDomain
import com.example.feature_main_screen.domain.model.TrendingMovieDomain

fun TrendingMovieDto.toTrendingMovieDomain(): TrendingMovieDomain {
    return TrendingMovieDomain(
        fullTitle = fullTitle,
        id = id,
        imDbRating = imDbRating,
        image = image
    )
}
fun ComingSoonMovieDto.toComingSoonMovieDomain(): ComingSoonMovieDomain {
    return ComingSoonMovieDomain(
        id = id,
        image = image,
        releaseState = releaseState,
        fullTitle = fullTitle,
        year = year
    )
}