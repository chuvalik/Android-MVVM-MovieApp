package com.example.feature_search_screen.data.repository

import androidx.room.withTransaction
import com.example.feature_core.utils.Resource
import com.example.feature_search_screen.data.local.SearchScreenDatabase
import com.example.feature_search_screen.data.mapper.toMovieDomain
import com.example.feature_search_screen.data.mapper.toMovieEntity
import com.example.feature_search_screen.data.remote.SearchScreenApi
import com.example.feature_search_screen.domain.model.MovieDomain
import com.example.feature_search_screen.domain.repository.SearchScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchScreenRepositoryImpl(
    private val api: SearchScreenApi,
    private val db: SearchScreenDatabase
) : SearchScreenRepository {

    private val dao = db.dao

    override suspend fun fetchMovies(query: String) = flow {
        emit(Resource.Loading())

        val localMovies = dao.fetchMovies(query).map { it.toMovieDomain() }
        emit(Resource.Success(localMovies))

        try {
            val remoteMovies = api.fetchMovies(expression = query)
            db.withTransaction {
                dao.deleteMovies(remoteMovies.results.map { it.title } )
                dao.insertMovies(remoteMovies.results.map { it.toMovieEntity() })
            }
        } catch (e: Exception) {
            emit(Resource.Error(error = e.message))
        }

        val newLocalData = dao.fetchMovies(query).map { it.toMovieDomain() }
        emit(Resource.Success(newLocalData))
    }
}