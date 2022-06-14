package com.example.feature_detail_screen.di

import androidx.room.Room
import com.example.feature_core.data.GsonParser
import com.example.feature_core.utils.Constants
import com.example.feature_detail_screen.data.local.DetailScreenDatabase
import com.example.feature_detail_screen.data.remote.DetailScreenApi
import com.example.feature_detail_screen.data.utils.Converters
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val detailScreenDataModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(DetailScreenApi::class.java)
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            DetailScreenDatabase::class.java,
            "movie_details_local_db"
        )
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }
}