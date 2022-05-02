package com.example.movieapp.feature_main_screen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.feature_main_screen.domain.model.NewMovieEntity

@Database(
    entities = [NewMovieEntity::class],
    version = 1
)
abstract class MainScreenDatabase : RoomDatabase() {

    abstract val dao: MainScreenDao
}