package com.example.feature_main_screen.data.local.trending_local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_main_screen.data.local.trending_local.entity.TrendingMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingMovieDao {

    @Query("SELECT * FROM trending_movie_local_db")
    suspend fun fetchMovies(): List<TrendingMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(trendingMovies: List<TrendingMovieEntity>)

    @Query("DELETE FROM trending_movie_local_db")
    suspend fun deleteMovies()
}