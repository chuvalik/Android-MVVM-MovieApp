package com.example.feature_detail_screen.data.remote

import com.example.feature_core.utils.Constants
import com.example.feature_detail_screen.data.remote.dto.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailScreenApi {

    @GET("en/API/Title")
    suspend fun fetchDetailMovie(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("id") id: String
    ): MovieDetailsResponse

}