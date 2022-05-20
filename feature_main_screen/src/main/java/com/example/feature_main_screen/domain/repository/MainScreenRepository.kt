package com.example.feature_main_screen.domain.repository

import com.example.feature_core.utils.Resource
import com.example.feature_main_screen.domain.model.NewMovieDomain
import kotlinx.coroutines.flow.Flow

interface MainScreenRepository {

    suspend fun fetchNewMovies(): Flow<Resource<List<NewMovieDomain>>>
}