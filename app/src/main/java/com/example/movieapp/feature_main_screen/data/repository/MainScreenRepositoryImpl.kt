package com.example.movieapp.feature_main_screen.data.repository

import com.example.movieapp.core.utils.Constants.UNEXPECTED_ERROR_MESSAGE
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.feature_main_screen.data.local.MainScreenDatabase
import com.example.movieapp.feature_main_screen.data.mapper.toNewMovieDomain
import com.example.movieapp.feature_main_screen.data.mapper.toNewMovieEntity
import com.example.movieapp.feature_main_screen.data.remote.MainScreenApi
import com.example.movieapp.feature_main_screen.domain.model.NewMovieDomain
import com.example.movieapp.feature_main_screen.domain.repository.MainScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class MainScreenRepositoryImpl(
    private val api: MainScreenApi,
    private val db: MainScreenDatabase
) : MainScreenRepository {

    private val dao = db.dao

    override suspend fun fetchNewMovies(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<NewMovieDomain>>> = flow {

        emit(Resource.Loading(isLoading = true))
        val localNewMovies = dao.getAllNewMoviesFromDatabase()
        emit(Resource.Success(
            localNewMovies.map { it.toNewMovieDomain() }
        ))

        val isDbEmpty = localNewMovies.isEmpty()
        val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote
        if (shouldLoadFromCache) {
            emit(Resource.Loading(false))
            return@flow
        }

        val remoteNewMovies = try {
            api.fetchNewMoviesFromApi().newMovies
        } catch (e: IOException) {
            emit(Resource.Error(error = e.message ?: UNEXPECTED_ERROR_MESSAGE))
            null
        } catch (e: IOException) {
            emit(Resource.Error(error = e.message ?: UNEXPECTED_ERROR_MESSAGE))
            null
        }

        remoteNewMovies?.let { newMovies ->
            dao.clearNewMoviesFromDatabase()
            dao.insertNewMoviesIntoDatabase(newMovies.map { it.toNewMovieEntity() })
            emit(Resource.Success(
                data = dao.getAllNewMoviesFromDatabase().map { it.toNewMovieDomain() })
            )
            emit(Resource.Loading(false))
        }
    }
}
