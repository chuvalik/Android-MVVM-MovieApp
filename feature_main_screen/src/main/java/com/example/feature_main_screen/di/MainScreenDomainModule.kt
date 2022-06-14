package com.example.feature_main_screen.di

import com.example.feature_main_screen.data.repository.MainScreenRepositoryImpl
import com.example.feature_main_screen.domain.repository.MainScreenRepository
import com.example.feature_main_screen.domain.use_cases.FetchComingSoonMoviesUseCase
import com.example.feature_main_screen.domain.use_cases.FetchTrendingMoviesUseCase
import org.koin.dsl.module

val mainScreenDomainModule = module {
    single<MainScreenRepository> {
        MainScreenRepositoryImpl(
            api = get(),
            trendingDb = get(),
            comingSoonDb = get()
        )
    }
    factory {
        FetchTrendingMoviesUseCase(repository = get())
    }
    factory {
        FetchComingSoonMoviesUseCase(repository = get())
    }
}