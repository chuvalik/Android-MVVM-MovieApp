package com.example.movieapp.feature_main_screen.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.feature_main_screen.domain.model.NewMovieEntity

@Dao
interface MainScreenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewMoviesIntoDatabase(
        newMoviesEntities: List<NewMovieEntity>
    )

    @Query("DELETE FROM main_screen_db")
    suspend fun clearNewMoviesFromDatabase()

    @Query("SELECT * FROM main_screen_db")
    fun getAllNewMoviesFromDatabase(): List<NewMovieEntity>
}