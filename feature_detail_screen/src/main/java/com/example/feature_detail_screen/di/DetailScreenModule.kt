package com.example.feature_detail_screen.di

import com.example.feature_core.utils.Constants
import com.example.feature_detail_screen.data.remote.DetailScreenApi
import com.example.feature_detail_screen.data.repository.DetailScreenRepositoryImpl
import com.example.feature_detail_screen.domain.repository.DetailScreenRepository
import com.example.feature_detail_screen.domain.use_cases.FetchDetailMovieUseCase
import com.example.feature_detail_screen.presentation.view_model.DetailScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val detailScreenModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(DetailScreenApi::class.java)
    }
    single<DetailScreenRepository> {
        DetailScreenRepositoryImpl(get())
    }
    viewModel {
        DetailScreenViewModel(get())
    }
    single {
        FetchDetailMovieUseCase(get())
    }
}