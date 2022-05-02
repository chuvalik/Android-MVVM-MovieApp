package com.example.movieapp.feature_main_screen.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.feature_main_screen.data.local.MainScreenDatabase
import com.example.movieapp.feature_main_screen.data.remote.MainScreenApi
import com.example.movieapp.feature_main_screen.data.repository.MainScreenRepositoryImpl
import com.example.movieapp.feature_main_screen.domain.repository.MainScreenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainScreenModule {

    @Singleton
    @Provides
    fun provideMainScreenApiInstance(): MainScreenApi {
        return Retrofit.Builder()
            .baseUrl(MainScreenApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MainScreenApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMainScreenRepositoryInstance(
        api: MainScreenApi,
        db: MainScreenDatabase
    ): MainScreenRepository {
        return MainScreenRepositoryImpl(api, db)
    }

    @Singleton
    @Provides
    fun provideMainScreenDatabaseInstance(app: Application): MainScreenDatabase {
        return Room.databaseBuilder(
            app,
            MainScreenDatabase::class.java,
            "main_screen_db"
        ).build()
    }
}