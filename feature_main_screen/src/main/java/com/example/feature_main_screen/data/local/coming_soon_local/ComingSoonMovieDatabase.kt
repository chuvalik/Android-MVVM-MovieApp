package com.example.feature_main_screen.data.local.coming_soon_local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_main_screen.data.local.coming_soon_local.entity.ComingSoonMovieEntity

@Database(
    entities = [ComingSoonMovieEntity::class],
    version = 1
)
abstract class ComingSoonMovieDatabase : RoomDatabase() {

    abstract val dao: ComingSoonMovieDao
}