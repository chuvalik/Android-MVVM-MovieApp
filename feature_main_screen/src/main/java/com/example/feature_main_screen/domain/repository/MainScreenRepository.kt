package com.example.feature_main_screen.domain.repository

import com.example.feature_core.utils.Resource
import com.example.feature_main_screen.domain.model.NewMovieDomain

interface MainScreenRepository {

    suspend fun fetchNewMovies(): Resource<List<NewMovieDomain>>
}