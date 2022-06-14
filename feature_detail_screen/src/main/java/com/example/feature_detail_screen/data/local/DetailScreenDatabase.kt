package com.example.feature_detail_screen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.feature_detail_screen.data.local.entity.MovieDetailsEntity
import com.example.feature_detail_screen.data.utils.Converters

@Database(
    entities = [MovieDetailsEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DetailScreenDatabase : RoomDatabase() {

    abstract val dao: DetailScreenDao
}