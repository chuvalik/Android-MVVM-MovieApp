package com.example.feature_search_screen.domain.repository

import com.example.feature_core.utils.Resource
import com.example.feature_search_screen.data.remote.dto.SearchMovieResponse
import com.example.feature_search_screen.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface SearchScreenRepository {

    suspend fun fetchMovies(): Flow<Resource<List<MovieDomain>>>
}