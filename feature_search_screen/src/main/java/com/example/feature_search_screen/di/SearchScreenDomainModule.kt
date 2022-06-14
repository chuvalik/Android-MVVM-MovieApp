package com.example.feature_search_screen.di

import com.example.feature_search_screen.data.repository.SearchScreenRepositoryImpl
import com.example.feature_search_screen.domain.repository.SearchScreenRepository
import com.example.feature_search_screen.domain.use_case.FetchMoviesUseCase
import org.koin.dsl.module

val searchScreenDomainModule = module {
    single<SearchScreenRepository> {
        SearchScreenRepositoryImpl(
            api = get(),
            db = get()
        )
    }

    factory {
        FetchMoviesUseCase(repository = get())
    }
}