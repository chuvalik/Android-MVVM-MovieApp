package com.example.feature_detail_screen.data.mapper

import com.example.feature_detail_screen.data.remote.dto.*
import com.example.feature_detail_screen.domain.model.*

fun MovieDetailsResponse.toMovieDetailsDomain(): MovieDetailsDomain {
    return MovieDetailsDomain(
        actorList = actorList.map { it.toActorDomain() },
        fullTitle = fullTitle,
        genres = genres,
        imDbRating = imDbRating,
        similars = similars.map { it.toSimilarDomain() },
        id = id,
        image = image,
        plot = plot,
        runtimeMins = runtimeMins
    )
}

fun ActorDto.toActorDomain(): ActorDomain {
    return ActorDomain(
        asCharacter = asCharacter,
        id = id,
        image = image,
        name = name
    )
}

fun SimilarDto.toSimilarDomain(): SimilarDomain {
    return SimilarDomain(
        id = id,
        imDbRating = imDbRating,
        image = image,
        title = title
    )
}