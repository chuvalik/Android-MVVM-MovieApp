package com.example.feature_search_screen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_search_screen.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class SearchScreenDatabase : RoomDatabase() {

    abstract val dao: SearchScreenDao
}