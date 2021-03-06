package com.example.feature_search_screen.presentation.adapter

import com.bumptech.glide.RequestManager
import com.example.feature_core.ui.BaseDiffUtilItemCallback
import com.example.feature_core.ui.DisplayableItem
import com.example.feature_search_screen.domain.model.MovieDomain
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MovieAdapter(
    glide: RequestManager,
    onGoToDetail: (MovieDomain) -> Unit
) : AsyncListDifferDelegationAdapter<DisplayableItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(
            SearchScreenAdapterDelegate.searchScreenAdapterDelegate(
                glide,
                onGoToDetail
            )
        )
    }
}