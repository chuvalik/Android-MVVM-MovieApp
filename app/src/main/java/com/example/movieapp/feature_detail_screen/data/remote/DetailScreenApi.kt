package com.example.movieapp.feature_detail_screen.data.remote

import com.example.movieapp.feature_detail_screen.data.remote.dto.DetailMovieDto
import retrofit2.http.GET

interface DetailScreenApi {

    @GET("7aeb0f89-d137-4449-a6a1-cd7b32d33030")
    suspend fun fetchDetailMovie(): DetailMovieDto

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}