package com.example.feature_detail_screen.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_detail_screen.data.local.entity.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailScreenDao {

    @Query("SELECT * FROM movie_details_local_db WHERE id = :id")
    suspend fun fetchMovie(id: String): MovieDetailsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieDetailsEntity)

    @Query("DELETE FROM movie_details_local_db WHERE id = :id")
    suspend fun deleteMovie(id: String)

    @Query("SELECT EXISTS (SELECT 1 FROM movie_details_local_db WHERE id = :id)")
    fun movieExists(id: String): Boolean
}