package com.example.feature_detail_screen.data.repository

import com.example.feature_core.utils.Constants.UNEXPECTED_ERROR_MESSAGE
import com.example.feature_core.utils.Resource
import com.example.feature_detail_screen.data.mapper.toMovieDetailsDomain
import com.example.feature_detail_screen.data.remote.DetailScreenApi
import com.example.feature_detail_screen.domain.repository.DetailScreenRepository
import kotlinx.coroutines.flow.flow

class DetailScreenRepositoryImpl(
    private val api: DetailScreenApi
) : DetailScreenRepository {

    override suspend fun fetchDetailMovie(id: String) = flow {

        emit(Resource.Loading())

        try {
            val remoteData = api.fetchDetailMovie(id = id).toMovieDetailsDomain()
            emit(Resource.Success(remoteData))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: UNEXPECTED_ERROR_MESSAGE))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: UNEXPECTED_ERROR_MESSAGE))
        }
    }
}