package com.example.feature_search_screen.data.remote

import com.example.feature_search_screen.data.remote.dto.SearchMovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchScreenApi {

    @GET("en/API/SearchMovie")
    @Headers(
        "apiKey : k_11vxci0s"
    )
    suspend fun fetchMovies(
        @Query ("apiKey") apiKey: String = "k_11vxci0s"
    ): SearchMovieResponse

    companion object {
        const val BASE_URL = "https://imdb-api.com/"
    }
}