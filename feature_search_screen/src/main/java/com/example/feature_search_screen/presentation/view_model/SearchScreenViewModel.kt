package com.example.feature_search_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_core.utils.Resource
import com.example.feature_search_screen.domain.use_case.FetchMoviesUseCase
import com.example.feature_search_screen.presentation.view_model.model.SearchScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    private val fetchMoviesUseCase: FetchMoviesUseCase
) : ViewModel() {

    private val _uiEvent = MutableStateFlow<SearchScreenState>(SearchScreenState.Empty)
    val uiEvent get() = _uiEvent.asStateFlow()

    private fun fetchMovies() = viewModelScope.launch {
        fetchMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {

                }
                is Resource.Loading -> {

                }
                else -> Unit
            }
        }
    }
}