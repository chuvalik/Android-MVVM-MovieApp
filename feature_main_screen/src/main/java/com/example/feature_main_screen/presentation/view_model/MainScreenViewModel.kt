package com.example.feature_main_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_core.utils.Constants
import com.example.feature_core.utils.DispatcherProvider
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
    private val fetchComingSoonMoviesUseCase: FetchComingSoonMoviesUseCase,
    private val dispatchers: DispatcherProvider
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
        viewModelScope.launch(dispatchers.io) {
            fetchTrendingMoviesUseCase().onEach { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { data ->
                            _uiTrendingMovieEvent.value = TrendingMovieEvent.Success(data = data)
                        }
                    }
                    is Resource.Error -> {
                        _uiTrendingMovieEvent.value = TrendingMovieEvent.Failure(
                            error = response.error ?: Constants.UNEXPECTED_ERROR_MESSAGE
                        )
                    }
                    is Resource.Loading -> _uiTrendingMovieEvent.value = TrendingMovieEvent.Loading
                }
            }.launchIn(this)
        }
    }

    private fun fetchComingSoonMovies() {
        viewModelScope.launch(dispatchers.io) {
            fetchComingSoonMoviesUseCase().onEach { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { data ->
                            _uiComingSoonMovieEvent.value = ComingSoonMovieEvent.Success(data = data)
                        }
                    }
                    is Resource.Error -> {
                        _uiComingSoonMovieEvent.value = ComingSoonMovieEvent.Failure(
                            error = response.error ?: Constants.UNEXPECTED_ERROR_MESSAGE
                        )
                    }
                    is Resource.Loading -> _uiComingSoonMovieEvent.value = ComingSoonMovieEvent.Loading
                }
            }.launchIn(this)
        }
    }
}