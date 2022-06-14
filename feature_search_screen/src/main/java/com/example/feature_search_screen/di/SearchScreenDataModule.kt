package com.example.feature_search_screen.di

import androidx.room.Room
import com.example.feature_core.utils.Constants
import com.example.feature_search_screen.data.local.SearchScreenDatabase
import com.example.feature_search_screen.data.remote.SearchScreenApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val searchScreenDataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchScreenApi::class.java)
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            SearchScreenDatabase::class.java,
            "search_screen_local_db"
        ).build()
    }
}