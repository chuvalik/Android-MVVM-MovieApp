package com.example.feature_detail_screen.presentation.adapter

import com.bumptech.glide.RequestManager
import com.example.feature_core.ui.DisplayableItem
import com.example.feature_detail_screen.databinding.CastItemBinding
import com.example.feature_detail_screen.domain.model.ActorDomain
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object DetailScreenAdapterDelegate {

    fun castAdapterDelegate(
        glide: RequestManager
    ) = adapterDelegateViewBinding<ActorDomain, DisplayableItem, CastItemBinding>(
        { layoutInflater, parent -> CastItemBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            binding.tvName.text = item.name
            binding.tvCharacterName.text = item.asCharacter
            glide.load(item.image).into(binding.ivActor)
        }
    }
}