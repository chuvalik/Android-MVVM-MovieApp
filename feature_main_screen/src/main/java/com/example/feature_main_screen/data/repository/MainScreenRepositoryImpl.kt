package com.example.feature_main_screen.data.repository

import com.example.feature_core.utils.Constants.UNEXPECTED_ERROR_MESSAGE
import com.example.feature_core.utils.Resource
import com.example.feature_main_screen.data.mapper.toNewMovieDomain
import com.example.feature_main_screen.data.remote.MainScreenApi
import com.example.feature_main_screen.domain.model.NewMovieDomain
import com.example.feature_main_screen.domain.repository.MainScreenRepository
import retrofit2.HttpException
import java.io.IOException

class MainScreenRepositoryImpl(
    private val api: MainScreenApi,
) : MainScreenRepository {

    override suspend fun fetchNewMovies(): Resource<List<NewMovieDomain>> {
        return try {
            val remoteData = api.fetchNewMoviesFromApi().newMovies.map { it.toNewMovieDomain() }
            Resource.Success(remoteData)
        } catch (e: IOException) {
            Resource.Error(error = e.message ?: UNEXPECTED_ERROR_MESSAGE)
        } catch (e: HttpException) {
            Resource.Error(error = e.message ?: UNEXPECTED_ERROR_MESSAGE)
        }
    }
}
