package com.example.feature_main_screen.di

import com.example.feature_main_screen.presentation.view_model.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreenPresentationModule = module {
    viewModel {
        MainScreenViewModel(
            fetchTrendingMoviesUseCase = get(),
            fetchComingSoonMoviesUseCase = get(),
            dispatchers = get()
        )
    }
}