package com.example.feature_main_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_core.utils.Resource
import com.example.feature_main_screen.domain.use_cases.FetchNewMoviesUseCase
import com.example.feature_main_screen.presentation.fragment.MainScreenEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class MainScreenViewModel(
    private val fetchNewMoviesUseCase: FetchNewMoviesUseCase
) : ViewModel() {

    private val _uiEvent = MutableStateFlow<MainScreenEvent>(MainScreenEvent.Empty)
    val uiEvent get() = _uiEvent.asStateFlow()

    init {
        getNewMovies()
    }

    private fun getNewMovies() {
        viewModelScope.launch {
            fetchNewMoviesUseCase().onEach { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { newMovies ->
                            _uiEvent.value = MainScreenEvent.Success(data = newMovies)
                        }
                    }
                    is Resource.Loading -> _uiEvent.value = MainScreenEvent.Loading
                    else -> Unit
                }
            }.launchIn(this)
        }
    }
}