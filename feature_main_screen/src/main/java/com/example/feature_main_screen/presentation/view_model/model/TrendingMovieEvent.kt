package com.example.feature_main_screen.presentation.view_model.model

import com.example.feature_main_screen.domain.model.TrendingMovieDomain

sealed class TrendingMovieEvent {
    class Success(val data: List<TrendingMovieDomain>) : TrendingMovieEvent()
    class Failure(val error: String): TrendingMovieEvent()
    object Loading : TrendingMovieEvent()
    object Empty: TrendingMovieEvent()
}