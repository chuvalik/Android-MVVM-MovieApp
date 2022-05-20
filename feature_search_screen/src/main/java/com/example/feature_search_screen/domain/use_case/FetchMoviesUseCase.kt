package com.example.feature_search_screen.domain.use_case

import com.example.feature_search_screen.domain.repository.SearchScreenRepository

class FetchMoviesUseCase(
    private val repository: SearchScreenRepository
) {

    suspend operator fun invoke() = repository.fetchMovies()
}