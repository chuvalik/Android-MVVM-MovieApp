package com.example.movieapp.feature_main_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.utils.Constants.UNEXPECTED_ERROR_MESSAGE
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.feature_main_screen.domain.repository.MainScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: MainScreenRepository
) : ViewModel() {

    private val _uiEvent = MutableStateFlow<MainScreenEvent>(MainScreenEvent.Empty)
    val uiEvent get() = _uiEvent.asStateFlow()

    init {
        getNewMovies()
    }

    private fun getNewMovies() {
        viewModelScope.launch {
            _uiEvent.value = MainScreenEvent.Loading
            when (val response = repository.fetchNewMovies()) {
                is Resource.Success -> {
                    _uiEvent.value = MainScreenEvent.Success(data = response.data!!)
                }
                is Resource.Error -> {
                    _uiEvent.value = MainScreenEvent.Failure(
                        error = response.error ?: UNEXPECTED_ERROR_MESSAGE
                    )
                }
                else -> Unit
            }
        }
    }
}