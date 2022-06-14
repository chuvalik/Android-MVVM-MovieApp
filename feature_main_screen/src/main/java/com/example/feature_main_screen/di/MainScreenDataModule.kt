package com.example.feature_main_screen.di

import android.app.Application
import androidx.room.Room
import com.example.feature_core.utils.Constants
import com.example.feature_main_screen.data.local.coming_soon_local.ComingSoonMovieDatabase
import com.example.feature_main_screen.data.local.trending_local.TrendingMovieDatabase
import com.example.feature_main_screen.data.remote.MainScreenApi
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainScreenDataModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(MainScreenApi::class.java)
    }

    fun provideComingSoonMovieDatabase(application: Application) = Room.databaseBuilder(
        application,
        ComingSoonMovieDatabase::class.java,
        "coming_soon_movie_local_db"
    ).build()

    fun provideComingSoonMovieDao(database: ComingSoonMovieDatabase) = database.dao

    single { provideComingSoonMovieDatabase(androidApplication()) }
    single { provideComingSoonMovieDao(database = get()) }

    fun provideTrendingMovieDatabase(application: Application) = Room.databaseBuilder(
        application,
        TrendingMovieDatabase::class.java,
        "trending_movie_local_db"
    ).build()

    fun provideTrendingMovieDao(database: TrendingMovieDatabase) = database.dao

    single { provideTrendingMovieDatabase(androidApplication()) }
    single { provideTrendingMovieDao(database = get()) }
}