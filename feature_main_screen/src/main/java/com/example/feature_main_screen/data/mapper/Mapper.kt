package com.example.feature_main_screen.data.mapper

import com.example.feature_main_screen.data.local.coming_soon_local.entity.ComingSoonMovieEntity
import com.example.feature_main_screen.data.local.trending_local.entity.TrendingMovieEntity
import com.example.feature_main_screen.data.remote.dto.coming_soon.ComingSoonMovieDto
import com.example.feature_main_screen.data.remote.dto.trending.TrendingMovieDto
import com.example.feature_main_screen.domain.model.ComingSoonMovieDomain
import com.example.feature_main_screen.domain.model.TrendingMovieDomain

fun TrendingMovieDto.toTrendingMovieEntity(): TrendingMovieEntity {
    return TrendingMovieEntity(
        fullTitle = fullTitle,
        id = id,
        imDbRating = imDbRating,
        image = image
    )
}

fun ComingSoonMovieDto.toComingSoonMovieEntity(): ComingSoonMovieEntity {
    return ComingSoonMovieEntity(
        id = id,
        image = image,
        releaseState = releaseState,
        fullTitle = fullTitle,
        year = year
    )
}

fun TrendingMovieEntity.toTrendingMovieDomain(): TrendingMovieDomain {
    return TrendingMovieDomain(
        fullTitle = fullTitle,
        id = id,
        imDbRating = imDbRating,
        image = image
    )
}

fun ComingSoonMovieEntity.toComingSoonMovieDomain(): ComingSoonMovieDomain {
    return ComingSoonMovieDomain(
        id = id,
        image = image,
        releaseState = releaseState,
        fullTitle = fullTitle,
        year = year
    )
}