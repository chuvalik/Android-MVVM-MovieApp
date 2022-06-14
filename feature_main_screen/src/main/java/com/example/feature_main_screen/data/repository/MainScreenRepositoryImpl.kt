package com.example.feature_main_screen.data.repository

import androidx.room.withTransaction
import com.example.feature_core.utils.Resource
import com.example.feature_main_screen.data.local.coming_soon_local.ComingSoonMovieDatabase
import com.example.feature_main_screen.data.local.trending_local.TrendingMovieDatabase
import com.example.feature_main_screen.data.mapper.toComingSoonMovieDomain
import com.example.feature_main_screen.data.mapper.toComingSoonMovieEntity
import com.example.feature_main_screen.data.mapper.toTrendingMovieDomain
import com.example.feature_main_screen.data.mapper.toTrendingMovieEntity
import com.example.feature_main_screen.data.remote.MainScreenApi
import com.example.feature_main_screen.domain.repository.MainScreenRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MainScreenRepositoryImpl(
    private val api: MainScreenApi,
    private val trendingDb: TrendingMovieDatabase,
    private val comingSoonDb: ComingSoonMovieDatabase
) : MainScreenRepository {

    private val trendingDao = trendingDb.dao

    override suspend fun fetchTrendingMovies() = flow {
        emit(Resource.Loading())

        val localMovies = trendingDao.fetchMovies().map { it.toTrendingMovieDomain() }
        emit(Resource.Success(localMovies))

        try {
            val remoteMovies = api.fetchTrendingMoviesFromApi()
            trendingDb.withTransaction {
                trendingDao.deleteMovies()
                trendingDao.insertMovies(remoteMovies.items.map { it.toTrendingMovieEntity() })
            }
        } catch (e: HttpException) {
            emit(Resource.Error(error = e.message))
        } catch (e: IOException) {
            emit(Resource.Error(error = e.message))
        }

        val newLocalData = trendingDao.fetchMovies().map { it.toTrendingMovieDomain() }
        emit(Resource.Success(newLocalData))
    }

    private val comingSoonDao = comingSoonDb.dao

    override suspend fun fetchComingSoonMovies() = flow {
        emit(Resource.Loading())

        val localMovies = comingSoonDao.fetchMovies().map { it.toComingSoonMovieDomain() }
        emit(Resource.Success(localMovies))

        try {
            val remoteMovies = api.fetchComingSoonMoviesFromApi()
            comingSoonDb.withTransaction {
                comingSoonDao.deleteMovies()
                comingSoonDao.insertMovies(remoteMovies.items.map { it.toComingSoonMovieEntity() })
            }
        } catch (e: Exception) {
            emit(Resource.Error(error = e.message))
        } catch (e: IOException) {
            emit(Resource.Error(error = e.message))
        }

        val newLocalData = comingSoonDao.fetchMovies().map { it.toComingSoonMovieDomain() }
        emit(Resource.Success(newLocalData))
    }
}
