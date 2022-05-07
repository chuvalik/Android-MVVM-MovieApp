package com.example.movieapp.feature_detail_screen.di

import com.example.movieapp.feature_detail_screen.data.remote.DetailScreenApi
import com.example.movieapp.feature_detail_screen.data.repository.DetailScreenRepositoryImpl
import com.example.movieapp.feature_detail_screen.domain.repository.DetailScreenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailScreenModule {

    @Singleton
    @Provides
    fun provideDetailScreenApiInstance(): DetailScreenApi {
        return Retrofit.Builder()
            .baseUrl(DetailScreenApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DetailScreenApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDetailScreenRepositoryInstance(api: DetailScreenApi): DetailScreenRepository {
        return DetailScreenRepositoryImpl(api)
    }
}