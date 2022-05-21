package com.example.feature_main_screen.presentation.view_model.model

import com.example.feature_main_screen.domain.model.ComingSoonMovieDomain

sealed class ComingSoonMovieEvent {
    class Success(val data: List<ComingSoonMovieDomain>) : ComingSoonMovieEvent()
    class Failure(val error: String) : ComingSoonMovieEvent()
    object Loading : ComingSoonMovieEvent()
    object Empty : ComingSoonMovieEvent()
}