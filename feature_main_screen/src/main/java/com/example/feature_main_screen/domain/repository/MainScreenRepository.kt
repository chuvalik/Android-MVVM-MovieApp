package com.example.feature_main_screen.domain.repository

import com.example.feature_core.utils.Resource
import com.example.feature_main_screen.domain.model.ComingSoonMovieDomain
import com.example.feature_main_screen.domain.model.TrendingMovieDomain
import kotlinx.coroutines.flow.Flow

interface MainScreenRepository {

    suspend fun fetchTrendingMovies(): Flow<Resource<List<TrendingMovieDomain>>>

    suspend fun fetchComingSoonMovies(): Flow<Resource<List<ComingSoonMovieDomain>>>
}