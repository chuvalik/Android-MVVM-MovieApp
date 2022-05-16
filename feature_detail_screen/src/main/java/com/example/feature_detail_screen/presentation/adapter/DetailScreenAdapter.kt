package com.example.feature_detail_screen.presentation.adapter

import com.bumptech.glide.RequestManager
import com.example.feature_core.ui.BaseDiffUtilItemCallback
import com.example.feature_core.ui.DisplayableItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class DetailScreenAdapter(
    private val glide: RequestManager
) : AsyncListDifferDelegationAdapter<DisplayableItem>(BaseDiffUtilItemCallback()){

    init {
        delegatesManager.addDelegate(DetailScreenAdapterDelegate.castAdapterDelegate(glide))
    }
}