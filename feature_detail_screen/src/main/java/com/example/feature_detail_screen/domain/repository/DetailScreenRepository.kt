package com.example.feature_detail_screen.domain.repository

import com.example.feature_core.utils.Resource
import com.example.feature_detail_screen.data.remote.dto.MovieDetailsResponse
import com.example.feature_detail_screen.domain.model.MovieDetailsDomain
import kotlinx.coroutines.flow.Flow

interface DetailScreenRepository {

    suspend fun fetchDetailMovie(id: String): Flow<Resource<MovieDetailsDomain>>
}