package com.example.feature_search_screen.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_search_screen.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchScreenDao {

    @Query("SELECT * FROM search_screen_local_db WHERE title LIKE '%' || :query || '%'")
    suspend fun fetchMovies(query: String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM search_screen_local_db WHERE title IN(:titles)")
    suspend fun deleteMovies(titles: List<String>)
}