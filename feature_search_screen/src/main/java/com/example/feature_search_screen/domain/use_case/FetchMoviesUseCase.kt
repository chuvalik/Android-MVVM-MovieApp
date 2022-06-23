package com.example.feature_search_screen.domain.use_case

import com.example.feature_core.utils.Resource
import com.example.feature_search_screen.domain.model.MovieDomain
import com.example.feature_search_screen.domain.repository.SearchScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchMoviesUseCase(
    private val repository: SearchScreenRepository
) {

    suspend operator fun invoke(query: String): Flow<Resource<List<MovieDomain>>> {
        if (query.isBlank()) {
            return flow { }
        }

        return repository.fetchMovies(query)
    }
}