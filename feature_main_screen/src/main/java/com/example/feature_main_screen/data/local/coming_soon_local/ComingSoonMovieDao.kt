package com.example.feature_main_screen.data.local.coming_soon_local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_main_screen.data.local.coming_soon_local.entity.ComingSoonMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComingSoonMovieDao {

    @Query("SELECT * FROM coming_soon_movie_local_db")
    suspend fun fetchMovies(): List<ComingSoonMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(comingSoonMovies: List<ComingSoonMovieEntity>)

    @Query("DELETE FROM coming_soon_movie_local_db")
    suspend fun deleteMovies()
}