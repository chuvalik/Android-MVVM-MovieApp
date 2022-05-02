package com.example.movieapp.feature_main_screen.data.remote

import com.example.movieapp.feature_main_screen.data.remote.dto.MainScreenResponse
import retrofit2.http.GET

interface MainScreenApi {

    @GET("540fd3be-0dea-407f-90aa-68a926faa3fd")
    suspend fun fetchNewMoviesFromApi(): MainScreenResponse

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}