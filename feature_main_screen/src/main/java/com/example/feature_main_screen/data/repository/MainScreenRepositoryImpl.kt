package com.example.feature_main_screen.data.repository

import com.example.feature_core.utils.Constants.UNEXPECTED_ERROR_MESSAGE
import com.example.feature_core.utils.Resource
import com.example.feature_main_screen.data.mapper.toComingSoonMovieDomain
import com.example.feature_main_screen.data.mapper.toTrendingMovieDomain
import com.example.feature_main_screen.data.remote.MainScreenApi
import com.example.feature_main_screen.domain.model.ComingSoonMovieDomain
import com.example.feature_main_screen.domain.repository.MainScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MainScreenRepositoryImpl(
    private val api: MainScreenApi,
) : MainScreenRepository {

    override suspend fun fetchTrendingMovies() = flow {
        emit(Resource.Loading())

        try {
            val remoteData = api.fetchTrendingMoviesFromApi().items.map { it.toTrendingMovieDomain() }
            emit(Resource.Success(remoteData))
        } catch (e: IOException) {
            emit(Resource.Error(error = e.message ?: UNEXPECTED_ERROR_MESSAGE))
        } catch (e: HttpException) {
            emit(Resource.Error(error = e.message ?: UNEXPECTED_ERROR_MESSAGE))
        }
    }

    override suspend fun fetchComingSoonMovies() = flow {
        emit(Resource.Loading())

        try {
            val remoteData = api.fetchComingSoonMoviesFromApi().items.map { it.toComingSoonMovieDomain() }
            emit(Resource.Success(remoteData))
        } catch (e: IOException) {
            emit(Resource.Error(error = e.message ?: UNEXPECTED_ERROR_MESSAGE))
        } catch (e: HttpException) {
            emit(Resource.Error(error = e.message ?: UNEXPECTED_ERROR_MESSAGE))
        }
    }


}
