package com.example.feature_detail_screen.presentation.view_model.model

import com.example.feature_detail_screen.domain.model.MovieDetailsDomain

sealed class DetailScreenEvent() {
    class Success(val data: MovieDetailsDomain) : DetailScreenEvent()
    class Failure(val error: String) : DetailScreenEvent()
    object Loading : DetailScreenEvent()
    object Empty : DetailScreenEvent()
}