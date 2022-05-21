package com.example.feature_search_screen.data.remote

import com.example.feature_core.utils.Constants
import com.example.feature_search_screen.data.remote.dto.SearchMovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchScreenApi {

    @GET("en/API/SearchMovie")
    suspend fun fetchMovies(
        @Query ("apiKey") apiKey: String = Constants.API_KEY,
        @Query ("expression") expression: String = "leon"
    ): SearchMovieResponse

}