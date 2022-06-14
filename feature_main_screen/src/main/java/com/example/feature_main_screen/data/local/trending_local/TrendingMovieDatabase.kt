package com.example.feature_main_screen.data.local.trending_local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_main_screen.data.local.trending_local.entity.TrendingMovieEntity

@Database(
    entities = [TrendingMovieEntity::class],
    version = 1
)
abstract class TrendingMovieDatabase : RoomDatabase() {

    abstract val dao: TrendingMovieDao
}