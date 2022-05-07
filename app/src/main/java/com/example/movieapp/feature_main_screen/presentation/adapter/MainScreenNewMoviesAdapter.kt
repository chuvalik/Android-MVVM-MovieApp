package com.example.movieapp.feature_main_screen.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.movieapp.databinding.AdapterNewMovieBinding
import com.example.movieapp.feature_main_screen.domain.model.NewMovieDomain

class MainScreenNewMoviesAdapter(
    private val glide: RequestManager
) : ListAdapter<NewMovieDomain, MainScreenNewMoviesAdapter.MainScreenNewMoviesViewHolder>(
    NewMoviesDiffCallback
) {

    class MainScreenNewMoviesViewHolder(
        private val binding: AdapterNewMovieBinding,
        private val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newMovie: NewMovieDomain) {
            binding.tvTitle.text = newMovie.title
            binding.tvGenre.text = newMovie.genre
            binding.tvMovieRating.text = newMovie.rating
            glide.load(newMovie.picture).into(binding.ivNewMovie)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainScreenNewMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MainScreenNewMoviesViewHolder(
            AdapterNewMovieBinding.inflate(layoutInflater, parent, false),
            glide
        )
    }

    override fun onBindViewHolder(holder: MainScreenNewMoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object NewMoviesDiffCallback : DiffUtil.ItemCallback<NewMovieDomain>() {
        override fun areItemsTheSame(oldItem: NewMovieDomain, newItem: NewMovieDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewMovieDomain, newItem: NewMovieDomain): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }
}