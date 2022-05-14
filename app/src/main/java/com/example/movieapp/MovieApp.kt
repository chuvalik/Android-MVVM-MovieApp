package com.example.movieapp

import android.app.Application
import com.example.feature_core.coreModule
import com.example.feature_detail_screen.di.detailScreenModule
import com.example.feature_main_screen.di.mainScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApp)
            modules(
                coreModule,
                mainScreenModule,
                detailScreenModule
            )
        }
    }
}