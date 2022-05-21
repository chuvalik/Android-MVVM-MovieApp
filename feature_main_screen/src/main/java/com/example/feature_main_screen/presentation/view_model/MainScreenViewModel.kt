package com.example.feature_main_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_core.utils.Resource
import com.example.feature_main_screen.domain.use_cases.FetchComingSoonMoviesUseCase
import com.example.feature_main_screen.domain.use_cases.FetchTrendingMoviesUseCase
import com.example.feature_main_screen.presentation.view_model.model.ComingSoonMovieEvent
import com.example.feature_main_screen.presentation.view_model.model.TrendingMovieEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class MainScreenViewModel(
    private val fetchTrendingMoviesUseCase: FetchTrendingMoviesUseCase,
    private val fetchComingSoonMoviesUseCase: FetchComingSoonMoviesUseCase
) : ViewModel() {

    private val _uiTrendingMovieEvent =
        MutableStateFlow<TrendingMovieEvent>(TrendingMovieEvent.Empty)
    val uiTrendingMovieEvent get() = _uiTrendingMovieEvent.asStateFlow()

    private val _uiComingSoonMovieEvent =
        MutableStateFlow<ComingSoonMovieEvent>(ComingSoonMovieEvent.Empty)
    val uiComingSoonMovieEvent get() = _uiComingSoonMovieEvent.asStateFlow()

    init {
        fetchTrendingMovies()
        fetchComingSoonMovies()
    }

    private fun fetchTrendingMovies() {
        viewModelScope.launch {
            fetchTrendingMoviesUseCase().onEach { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { data ->
                            _uiTrendingMovieEvent.value = TrendingMovieEvent.Success(data = data)
                        }
                    }
                    is Resource.Loading -> _uiTrendingMovieEvent.value = TrendingMovieEvent.Loading
                    else -> Unit
                }
            }.launchIn(this)
        }
    }

    private fun fetchComingSoonMovies() {
        viewModelScope.launch {
            fetchComingSoonMoviesUseCase().onEach { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { data ->
                            _uiComingSoonMovieEvent.value = ComingSoonMovieEvent.Success(data = data)
                        }
                    }
                    is Resource.Loading -> _uiComingSoonMovieEvent.value = ComingSoonMovieEvent.Loading
                    else -> Unit
                }
            }.launchIn(this)
        }
    }
}