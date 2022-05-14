package com.example.feature_main_screen.data.remote

import com.example.feature_main_screen.data.remote.dto.MainScreenResponse
import retrofit2.http.GET

interface MainScreenApi {

    @GET("540fd3be-0dea-407f-90aa-68a926faa3fd")
    suspend fun fetchNewMoviesFromApi(): MainScreenResponse
}