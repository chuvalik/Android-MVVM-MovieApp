package com.example.feature_main_screen.presentation.adapter

import com.bumptech.glide.RequestManager
import com.example.feature_core.ui.BaseDiffUtilItemCallback
import com.example.feature_core.ui.DisplayableItem
import com.example.feature_main_screen.domain.model.NewMovieDomain
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class TrendingMoviesAdapter(
    private val glide: RequestManager,
    private val onGoToDetail: (NewMovieDomain) -> Unit
) : AsyncListDifferDelegationAdapter<DisplayableItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(
            MainScreenAdapterDelegate.trendingMoviesAdapterDelegate(
                glide,
                onGoToDetail
            )
        )
    }
}