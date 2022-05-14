package com.example.feature_main_screen.di

import com.example.feature_core.utils.Constants
import com.example.feature_main_screen.data.remote.MainScreenApi
import com.example.feature_main_screen.data.repository.MainScreenRepositoryImpl
import com.example.feature_main_screen.domain.repository.MainScreenRepository
import com.example.feature_main_screen.domain.use_cases.FetchNewMoviesUseCase
import com.example.feature_main_screen.presentation.view_model.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainScreenModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(MainScreenApi::class.java)
    }
    single<MainScreenRepository> {
        MainScreenRepositoryImpl(get())
    }
    viewModel {
        MainScreenViewModel(get())
    }
    single {
        FetchNewMoviesUseCase(get())
    }
}