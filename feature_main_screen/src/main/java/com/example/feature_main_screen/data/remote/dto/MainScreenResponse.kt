package com.example.feature_main_screen.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MainScreenResponse(
    @SerializedName("new_movies")
    val newMovies: List<NewMovieDto>
)