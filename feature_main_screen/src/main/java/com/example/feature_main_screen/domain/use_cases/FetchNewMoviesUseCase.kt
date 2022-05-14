package com.example.feature_main_screen.domain.use_cases

import com.example.feature_main_screen.domain.repository.MainScreenRepository

class FetchNewMoviesUseCase(
    private val repository: MainScreenRepository
) {

    suspend operator fun invoke() = repository.fetchNewMovies()
}