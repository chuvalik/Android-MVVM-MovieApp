package com.example.feature_detail_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_core.utils.Resource
import com.example.feature_detail_screen.domain.repository.DetailScreenRepository
import com.example.feature_detail_screen.domain.use_cases.FetchDetailMovieUseCase
import com.example.feature_detail_screen.presentation.fragment.DetailScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DetailScreenViewModel(
    private val fetchDetailMovieUseCase: FetchDetailMovieUseCase
) : ViewModel() {

    private val _uiEvent = MutableStateFlow<DetailScreenEvent>(DetailScreenEvent.Empty)
    val uiEvent get() = _uiEvent.asStateFlow()

    init {
        fetchDetailMovie()
    }

    private fun fetchDetailMovie() {
        viewModelScope.launch {
            _uiEvent.value = DetailScreenEvent.Loading
            when (val response = fetchDetailMovieUseCase()) {
                is Resource.Success -> {
                    response.data?.let { detailMovie ->
                        _uiEvent.value = DetailScreenEvent.Success(data = detailMovie)
                    }
                }
                is Resource.Error -> Unit // TODO: Handle Resource.Error
            }
        }
    }
}