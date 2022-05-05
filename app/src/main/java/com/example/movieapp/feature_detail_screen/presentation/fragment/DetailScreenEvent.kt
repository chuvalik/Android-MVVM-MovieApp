package com.example.movieapp.feature_detail_screen.presentation.fragment

import com.example.movieapp.feature_detail_screen.domain.model.DetailMovieDomain

sealed class DetailScreenEvent() {
    class Success(val data: DetailMovieDomain) : DetailScreenEvent()
    class Failure(val error: String) : DetailScreenEvent()
    object Loading : DetailScreenEvent()
    object Empty : DetailScreenEvent()
}