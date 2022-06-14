package com.example.feature_detail_screen.data.mapper

import android.util.Log
import com.example.feature_detail_screen.data.local.entity.ActorEntity
import com.example.feature_detail_screen.data.local.entity.MovieDetailsEntity
import com.example.feature_detail_screen.data.remote.dto.*
import com.example.feature_detail_screen.domain.model.*

fun MovieDetailsResponse.toMovieDetailsEntity (): MovieDetailsEntity {
    return MovieDetailsEntity(
        actorList = actorList?.map { it.toActorEntity() },
        fullTitle = fullTitle,
        genres = genres,
        imDbRating = imDbRating,
        id = id,
        image = image,
        plot = plot,
        runtimeMins = runtimeMins
    )
}

fun ActorDto.toActorEntity(): ActorEntity {
    return ActorEntity(
        asCharacter = asCharacter,
        id = id,
        image = image,
        name = name
    )
}

fun MovieDetailsEntity.toMovieDetailsDomain (): MovieDetailsDomain {
    return MovieDetailsDomain(
        actorList = actorList?.map { it.toActorDomain() },
        fullTitle = fullTitle,
        genres = genres,
        imDbRating = imDbRating,
        image = image,
        plot = plot,
        runtimeMins = runtimeMins
    )
}

fun ActorEntity.toActorDomain (): ActorDomain {
    return ActorDomain(
        asCharacter = asCharacter,
        id = id,
        image = image,
        name = name
    )
}

