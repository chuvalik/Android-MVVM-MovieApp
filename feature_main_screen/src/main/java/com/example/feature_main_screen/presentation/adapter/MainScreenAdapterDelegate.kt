package com.example.feature_main_screen.presentation.adapter

import com.bumptech.glide.RequestManager
import com.example.feature_core.ui.DisplayableItem
import com.example.feature_main_screen.databinding.AdapterMovieItemBinding
import com.example.feature_main_screen.databinding.ViewPagerItemBinding
import com.example.feature_main_screen.domain.model.ComingSoonMovieDomain
import com.example.feature_main_screen.domain.model.TrendingMovieDomain
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object MainScreenAdapterDelegate {

    fun comingSoonMoviesAdapterDelegate(glide: RequestManager) =
        adapterDelegateViewBinding<ComingSoonMovieDomain, DisplayableItem, ViewPagerItemBinding>(
            { layoutInflater, parent ->
                ViewPagerItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            }
        ) {
            bind {
                binding.tvTitle.text = item.fullTitle
                glide.load(item.image).into(binding.ivMovie)
            }
        }

    fun trendingMoviesAdapterDelegate(
        glide: RequestManager,
        onGoToDetail: (TrendingMovieDomain) -> Unit
    ) = adapterDelegateViewBinding<TrendingMovieDomain, DisplayableItem, AdapterMovieItemBinding>(
        { layoutInflater, parent ->
            AdapterMovieItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {
        bind {
            glide.load(item.image).into(binding.ivMovie)
            binding.tvTitle.text = item.fullTitle
            binding.tvRating.text = item.imDbRating

            binding.ivMovie.setOnClickListener {
                onGoToDetail(item)
            }
        }
    }
}