package com.example.feature_main_screen.presentation.adapter

import androidx.core.view.isVisible
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
            binding.tvTitle.text =
                if (item.fullTitle.length > 14) item.fullTitle.substring(0, 12) + "..."
                else item.fullTitle

            // Available only for released movies
            if (item.imDbRating.isNotBlank()) {
                binding.tvRating.text = item.imDbRating
                binding.tvRating.isVisible = true
                binding.ivStar.isVisible = true
                binding.tvComingSoon.isVisible = false

                binding.ivMovie.setOnClickListener {
                    onGoToDetail(item)
                }
            }
        }
    }
}