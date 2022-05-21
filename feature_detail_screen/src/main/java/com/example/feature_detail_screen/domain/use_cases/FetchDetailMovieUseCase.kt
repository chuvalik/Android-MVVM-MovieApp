package com.example.feature_detail_screen.domain.use_cases

import com.example.feature_detail_screen.domain.repository.DetailScreenRepository

class FetchDetailMovieUseCase(
    private val repository: DetailScreenRepository
) {

    suspend operator fun invoke(id: String) = repository.fetchDetailMovie(id = id)
}