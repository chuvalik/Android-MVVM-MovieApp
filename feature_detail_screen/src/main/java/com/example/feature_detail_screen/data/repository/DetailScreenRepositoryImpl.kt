package com.example.feature_detail_screen.data.repository

import com.example.feature_core.utils.Constants.UNEXPECTED_ERROR_MESSAGE
import com.example.feature_core.utils.Resource
import com.example.feature_detail_screen.data.mapper.toDetailMovieDomain
import com.example.feature_detail_screen.data.remote.DetailScreenApi
import com.example.feature_detail_screen.domain.model.DetailMovieDomain
import com.example.feature_detail_screen.domain.repository.DetailScreenRepository

class DetailScreenRepositoryImpl(
    private val api: DetailScreenApi
): DetailScreenRepository {

    override suspend fun fetchDetailMovie(): Resource<DetailMovieDomain> {
        return try {
            val remoteData = api.fetchDetailMovie()
            Resource.Success(remoteData.toDetailMovieDomain())
        } catch (e: Exception) {
            Resource.Error(e.message ?: UNEXPECTED_ERROR_MESSAGE)
        } catch (e: Exception) {
            Resource.Error(e.message ?: UNEXPECTED_ERROR_MESSAGE)
        }
    }
}