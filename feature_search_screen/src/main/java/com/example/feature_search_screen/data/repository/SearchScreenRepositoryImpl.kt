package com.example.feature_search_screen.data.repository

import com.example.feature_core.utils.Resource
import com.example.feature_search_screen.data.mapper.toMovieDomain
import com.example.feature_search_screen.data.remote.SearchScreenApi
import com.example.feature_search_screen.domain.repository.SearchScreenRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SearchScreenRepositoryImpl(
    private val api: SearchScreenApi
) : SearchScreenRepository {

    override suspend fun fetchMovies() = flow {

        emit(Resource.Loading())

        try {
            val remoteData = api.fetchMovies().results
            val domainData = remoteData.map { it.toMovieDomain() }
            emit(Resource.Success(data = domainData))
        } catch (e: IOException) {
            emit(Resource.Error(error = e.message))
        } catch (e: HttpException) {
            emit(Resource.Error(error = e.message))
        }
    }
}