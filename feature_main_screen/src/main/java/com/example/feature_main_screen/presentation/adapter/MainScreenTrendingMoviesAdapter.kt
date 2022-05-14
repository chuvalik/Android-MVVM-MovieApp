package com.example.feature_main_screen.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_main_screen.databinding.ViewPagerItemBinding
import com.example.feature_main_screen.domain.model.NewMovieDomain

class MainScreenTrendingMoviesAdapter(
    private val glide: RequestManager,
    private val onGoToDetail: (NewMovieDomain) -> Unit // TODO: replace NewMovieDomain
) : ListAdapter<NewMovieDomain, MainScreenTrendingMoviesAdapter.MainScreenTrendingMoviesViewHolder>(
    TrendingMoviesDiffCallback
) {

    class MainScreenTrendingMoviesViewHolder(
        private val binding: ViewPagerItemBinding,
        private val glide: RequestManager,
        private val onGoToDetail: (NewMovieDomain) -> Unit // TODO: replace NewMovieDomain
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newMovieDomain: NewMovieDomain) {
            glide.load(newMovieDomain.picture).into(binding.ivMovie)
            binding.tvTitle.text = newMovieDomain.title
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainScreenTrendingMoviesViewHolder {
        return MainScreenTrendingMoviesViewHolder(
            ViewPagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            glide,
            onGoToDetail
        )
    }

    override fun onBindViewHolder(holder: MainScreenTrendingMoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object TrendingMoviesDiffCallback : DiffUtil.ItemCallback<NewMovieDomain>() {
        override fun areItemsTheSame(oldItem: NewMovieDomain, newItem: NewMovieDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewMovieDomain, newItem: NewMovieDomain): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
}