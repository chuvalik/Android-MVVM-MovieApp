package com.example.feature_search_screen.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_core.utils.Constants
import com.example.feature_core.utils.DispatcherProvider
import com.example.feature_core.utils.Resource
import com.example.feature_search_screen.domain.use_case.FetchMoviesUseCase
import com.example.feature_search_screen.presentation.view_model.model.SearchScreenState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _searchEvent = MutableStateFlow<SearchScreenState>(SearchScreenState.Empty)
    val searchEvent get() = _searchEvent.asStateFlow()

    private var searchJob: Job? = null

    fun fetchMovies(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(dispatchers.io) {
            delay(Constants.API_CALL_DELAY)
            fetchMoviesUseCase(query).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { data ->
                            _searchEvent.value = SearchScreenState.Success(data = data)
                        }
                    }
                    is Resource.Loading -> {
                        _searchEvent.value = SearchScreenState.Loading
                    }
                    is Resource.Error -> {
                        _searchEvent.value = SearchScreenState.Failure(
                            error = result.error ?: Constants.UNEXPECTED_ERROR_MESSAGE
                        )
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }

}