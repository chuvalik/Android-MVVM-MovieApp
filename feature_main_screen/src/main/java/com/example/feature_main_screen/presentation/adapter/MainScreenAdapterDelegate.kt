package com.example.feature_main_screen.presentation.adapter

import com.bumptech.glide.RequestManager
import com.example.feature_core.ui.DisplayableItem
import com.example.feature_main_screen.databinding.AdapterMovieItemBinding
import com.example.feature_main_screen.databinding.ViewPagerItemBinding
import com.example.feature_main_screen.domain.model.NewMovieDomain
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object MainScreenAdapterDelegate {

    fun newMoviesAdapterDelegate(glide: RequestManager) =
        adapterDelegateViewBinding<NewMovieDomain, DisplayableItem, AdapterMovieItemBinding>(
            { layoutInflater, parent ->
                AdapterMovieItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            }
        ) {
            bind {
                binding.tvTitle.text = item.title
                binding.tvGenre.text = item.genre
                binding.tvMovieRating.text = item.rating
                glide.load(item.picture).into(binding.ivNewMovie)
            }
        }

    fun trendingMoviesAdapterDelegate(
        glide: RequestManager,
        onGoToDetail: (NewMovieDomain) -> Unit
    ) =
        adapterDelegateViewBinding<NewMovieDomain, DisplayableItem, ViewPagerItemBinding>(
            { layoutInflater, parent ->
                ViewPagerItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            }
        ) {
            bind {
                glide.load(item.picture).into(binding.ivMovie)
                binding.tvTitle.text = item.title

                binding.ivMovie.setOnClickListener {
                    onGoToDetail(item)
                }
            }
        }
}