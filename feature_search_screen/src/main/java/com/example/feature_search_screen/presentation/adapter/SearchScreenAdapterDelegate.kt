package com.example.feature_search_screen.presentation.adapter

import com.bumptech.glide.RequestManager
import com.example.feature_core.ui.DisplayableItem
import com.example.feature_search_screen.databinding.ItemMovieBinding
import com.example.feature_search_screen.domain.model.MovieDomain
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object SearchScreenAdapterDelegate {

    fun searchScreenAdapterDelegate(
        glide: RequestManager
    ) = adapterDelegateViewBinding<MovieDomain, DisplayableItem, ItemMovieBinding>(
        { layoutInflater, parent -> ItemMovieBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            // TODO
        }
    }
}