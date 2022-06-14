package com.example.feature_detail_screen.data.repository

import androidx.room.withTransaction
import com.example.feature_core.utils.Resource
import com.example.feature_detail_screen.data.local.DetailScreenDatabase
import com.example.feature_detail_screen.data.mapper.toMovieDetailsDomain
import com.example.feature_detail_screen.data.mapper.toMovieDetailsEntity
import com.example.feature_detail_screen.data.remote.DetailScreenApi
import com.example.feature_detail_screen.domain.model.MovieDetailsDomain
import com.example.feature_detail_screen.domain.repository.DetailScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailScreenRepositoryImpl(
    private val api: DetailScreenApi,
    private val db: DetailScreenDatabase
) : DetailScreenRepository {

    private val dao = db.dao

    override suspend fun fetchDetailMovie(id: String): Flow<Resource<MovieDetailsDomain>> = flow {
        emit(Resource.Loading())

        if (dao.movieExists(id = id)) {
            val localMovies = dao.fetchMovie(id).toMovieDetailsDomain()
            emit(Resource.Success(localMovies))
        }

        try {
            val remoteMovies = api.fetchDetailMovie(id = id)
            db.withTransaction {
                dao.deleteMovie(id = id)
                dao.insertMovie(remoteMovies.toMovieDetailsEntity())
            }
        } catch (e: Exception) {
            emit(Resource.Error(error = e.message))
        }

        if (dao.movieExists(id = id)) {
            val newLocalData = dao.fetchMovie(id).toMovieDetailsDomain()
            emit(Resource.Success(newLocalData))
        }
    }

}