package com.example.movieapp.feature_main_screen.domain.repository

import com.example.movieapp.core.utils.Resource
import com.example.movieapp.feature_main_screen.domain.model.NewMovieDomain
import kotlinx.coroutines.flow.Flow

interface MainScreenRepository {

    suspend fun fetchNewMovies(fetchFromRemote: Boolean): Flow<Resource<List<NewMovieDomain>>>
}