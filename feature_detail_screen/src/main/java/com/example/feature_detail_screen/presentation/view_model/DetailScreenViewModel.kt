package com.example.feature_detail_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_core.utils.Constants
import com.example.feature_core.utils.Resource
import com.example.feature_detail_screen.domain.use_cases.FetchDetailMovieUseCase
import com.example.feature_detail_screen.presentation.view_model.model.DetailScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class DetailScreenViewModel(
    private val fetchDetailMovieUseCase: FetchDetailMovieUseCase
) : ViewModel() {

    private val _uiEvent = MutableStateFlow<DetailScreenEvent>(DetailScreenEvent.Empty)
    val uiEvent get() = _uiEvent.asStateFlow()

    var movieId: String = ""


    fun fetchDetailMovie(id: String) = viewModelScope.launch {
        fetchDetailMovieUseCase(
            id
        ).onEach { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { detailMovie ->
                        _uiEvent.value = DetailScreenEvent.Success(data = detailMovie)
                    }
                }
                is Resource.Loading -> _uiEvent.value = DetailScreenEvent.Loading
                is Resource.Error -> {
                    _uiEvent.value = DetailScreenEvent.Failure(
                        error = response.error ?: Constants.UNEXPECTED_ERROR_MESSAGE
                    )
                }
            }
        }.launchIn(this)
    }

}