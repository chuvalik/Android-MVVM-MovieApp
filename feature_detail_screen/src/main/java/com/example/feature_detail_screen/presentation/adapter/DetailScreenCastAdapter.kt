package com.example.feature_detail_screen.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_detail_screen.databinding.AdapterCastItemBinding
import com.example.feature_detail_screen.domain.model.CastDomain

class DetailScreenCastAdapter(
    private val glide: RequestManager
) : ListAdapter<CastDomain, DetailScreenCastAdapter.CastViewHolder>(CastDiffCallback) {

    class CastViewHolder(
        private val binding: AdapterCastItemBinding,
        private val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cast: CastDomain) {
            binding.tvName.text = cast.name
            binding.tvCharacterName.text = cast.characterName
            glide.load(cast.picture).into(binding.ivActor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            AdapterCastItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            glide
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object CastDiffCallback : DiffUtil.ItemCallback<CastDomain>() {
        override fun areItemsTheSame(oldItem: CastDomain, newItem: CastDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CastDomain, newItem: CastDomain): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
}