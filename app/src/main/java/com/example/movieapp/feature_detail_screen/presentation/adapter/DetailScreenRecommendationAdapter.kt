package com.example.movieapp.feature_detail_screen.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.movieapp.databinding.AdapterRecommendedItemBinding
import com.example.movieapp.feature_main_screen.domain.model.NewMovieDomain

class DetailScreenRecommendationAdapter(
    private val glide: RequestManager
) : ListAdapter<NewMovieDomain, DetailScreenRecommendationAdapter.RecommendedMovieViewHolder>(
    RecommendationDiffCallback
) {

    class RecommendedMovieViewHolder(
        private val binding: AdapterRecommendedItemBinding,
        private val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newMovie: NewMovieDomain) { // TODO: replace newMovie with recommendedMovie
            binding.tvTitle.text = newMovie.title
            binding.tvGenre.text = newMovie.genre
            binding.tvMovieRating.text = newMovie.rating
            glide.load(newMovie.picture).into(binding.ivNewMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedMovieViewHolder {
        return RecommendedMovieViewHolder(
            AdapterRecommendedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            glide
        )
    }

    override fun onBindViewHolder(holder: RecommendedMovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object RecommendationDiffCallback : DiffUtil.ItemCallback<NewMovieDomain>() {
        override fun areItemsTheSame(oldItem: NewMovieDomain, newItem: NewMovieDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewMovieDomain, newItem: NewMovieDomain): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
}