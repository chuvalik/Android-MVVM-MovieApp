package com.example.feature_detail_screen.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details_local_db")
data class MovieDetailsEntity(
    val actorList: List<ActorEntity>,
    val fullTitle: String,
    val genres: String,
    @PrimaryKey val id: String,
    val imDbRating: String,
    val image: String,
    val plot: String,
    val runtimeMins: String,
)