package com.example.movieapp.feature_detail_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.feature_detail_screen.domain.repository.DetailScreenRepository
import com.example.movieapp.feature_detail_screen.presentation.fragment.DetailScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: DetailScreenRepository
) : ViewModel() {

    private val _uiEvent = MutableStateFlow<DetailScreenEvent>(DetailScreenEvent.Empty)
    val uiEvent get() = _uiEvent.asStateFlow()

    init {
        fetchDetailMovie()
    }

    private fun fetchDetailMovie() {
        viewModelScope.launch {
            _uiEvent.value = DetailScreenEvent.Loading
            when (val response = repository.fetchDetailMovieFromApi()) {
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