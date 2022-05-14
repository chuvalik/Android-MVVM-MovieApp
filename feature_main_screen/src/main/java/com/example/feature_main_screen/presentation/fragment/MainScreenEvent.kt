package com.example.feature_main_screen.presentation.fragment

import com.example.feature_main_screen.domain.model.NewMovieDomain

sealed class MainScreenEvent {
    class Success(val data: List<NewMovieDomain>) : MainScreenEvent()
    class Failure(val error: String): MainScreenEvent()
    object Loading : MainScreenEvent()
    object Empty: MainScreenEvent()
}