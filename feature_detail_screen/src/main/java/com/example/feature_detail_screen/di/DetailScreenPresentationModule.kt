package com.example.feature_detail_screen.di

import com.example.feature_detail_screen.presentation.view_model.DetailScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailScreenPresentationModule = module {
    viewModel {
        DetailScreenViewModel(fetchDetailMovieUseCase = get(), dispatchers = get())
    }
}