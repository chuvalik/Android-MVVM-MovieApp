package com.example.feature_main_screen.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_main_screen.databinding.AdapterMovieItemBinding
import com.example.feature_main_screen.domain.model.NewMovieDomain

class MainScreenNewMoviesAdapter(
    private val glide: RequestManager,
    private val onGoToDetail: (NewMovieDomain) -> Unit
) : ListAdapter<NewMovieDomain, MainScreenNewMoviesAdapter.MainScreenNewMoviesViewHolder>(
    NewMoviesDiffCallback
) {

    class MainScreenNewMoviesViewHolder(
        private val binding: AdapterMovieItemBinding,
        private val glide: RequestManager,
        private val onGoToDetail: (NewMovieDomain) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(newMovie: NewMovieDomain) {
            binding.tvTitle.text = newMovie.title
            binding.tvGenre.text = newMovie.genre
            binding.tvMovieRating.text = newMovie.rating
            glide.load(newMovie.picture).into(binding.ivNewMovie)
            binding.cvNewMovie.setOnClickListener {
                onGoToDetail(newMovie)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainScreenNewMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MainScreenNewMoviesViewHolder(
            AdapterMovieItemBinding.inflate(layoutInflater, parent, false),
            glide,
            onGoToDetail
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