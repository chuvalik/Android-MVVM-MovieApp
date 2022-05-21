package com.example.feature_main_screen.data.remote

import com.example.feature_core.utils.Constants
import com.example.feature_main_screen.data.remote.dto.coming_soon.ComingSoonResponse
import com.example.feature_main_screen.data.remote.dto.new_movie.TrendingMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainScreenApi {

    @GET("en/API/MostPopularMovies")
    suspend fun fetchTrendingMoviesFromApi(
        @Query ("apiKey") apiKey: String = Constants.API_KEY
    ): TrendingMovieResponse

    @GET("/en/API/ComingSoon")
    suspend fun fetchComingSoonMoviesFromApi(
        @Query ("apiKey") apiKey: String = Constants.API_KEY
    ): ComingSoonResponse
}