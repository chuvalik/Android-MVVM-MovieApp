package com.example.feature_core

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single {
        Glide.with(androidContext()).setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.background_place_holder)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
    }
}