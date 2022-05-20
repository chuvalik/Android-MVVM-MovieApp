package com.example.feature_search_screen.di

import com.example.feature_search_screen.data.remote.SearchScreenApi
import com.example.feature_search_screen.data.repository.SearchScreenRepositoryImpl
import com.example.feature_search_screen.domain.repository.SearchScreenRepository
import com.example.feature_search_screen.domain.use_case.FetchMoviesUseCase
import com.example.feature_search_screen.presentation.view_model.SearchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val searchScreenModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(SearchScreenApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchScreenApi::class.java)
    }
    single<SearchScreenRepository> {
        SearchScreenRepositoryImpl(api = get())
    }
    single {
        FetchMoviesUseCase(repository = get())
    }
    viewModel {
        SearchScreenViewModel(fetchMoviesUseCase = get())
    }
}