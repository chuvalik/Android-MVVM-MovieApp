package com.example.feature_search_screen.presentation.view_model.model

import com.example.feature_search_screen.domain.model.MovieDomain

sealed class SearchScreenState {
    class Success(val data: List<MovieDomain>) : SearchScreenState()
    class Failure(val error: String): SearchScreenState()
    object Loading : SearchScreenState()
    object Empty: SearchScreenState()
}