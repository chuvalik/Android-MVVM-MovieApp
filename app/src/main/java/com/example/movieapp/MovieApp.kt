package com.example.movieapp

import android.app.Application
import com.example.feature_core.coreModule
import com.example.feature_detail_screen.di.detailScreenDataModule
import com.example.feature_detail_screen.di.detailScreenDomainModule
import com.example.feature_detail_screen.di.detailScreenPresentationModule
import com.example.feature_main_screen.di.mainScreenDataModule
import com.example.feature_main_screen.di.mainScreenDomainModule
import com.example.feature_main_screen.di.mainScreenPresentationModule
import com.example.feature_search_screen.di.searchScreenDataModule
import com.example.feature_search_screen.di.searchScreenDomainModule
import com.example.feature_search_screen.di.searchScreenPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApp)
            modules(
                coreModule,
                mainScreenDataModule,
                mainScreenDomainModule,
                mainScreenPresentationModule,
                detailScreenDataModule,
                detailScreenDomainModule,
                detailScreenPresentationModule,
                searchScreenDataModule,
                searchScreenDomainModule,
                searchScreenPresentationModule
            )
        }
    }
}