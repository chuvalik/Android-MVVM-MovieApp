package com.example.feature_search_screen.di

import com.example.feature_search_screen.presentation.view_model.SearchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchScreenPresentationModule = module {
    viewModel {
        SearchScreenViewModel(
            fetchMoviesUseCase = get(),
            dispatchers = get()
        )
    }
}