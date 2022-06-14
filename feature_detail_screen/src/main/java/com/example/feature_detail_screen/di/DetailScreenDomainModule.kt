package com.example.feature_detail_screen.di

import com.example.feature_detail_screen.data.repository.DetailScreenRepositoryImpl
import com.example.feature_detail_screen.domain.repository.DetailScreenRepository
import com.example.feature_detail_screen.domain.use_cases.FetchDetailMovieUseCase
import org.koin.dsl.module

val detailScreenDomainModule = module {
    single<DetailScreenRepository> {
        DetailScreenRepositoryImpl(
            api = get(),
            db = get()
        )
    }

    factory {
        FetchDetailMovieUseCase(get())
    }
}