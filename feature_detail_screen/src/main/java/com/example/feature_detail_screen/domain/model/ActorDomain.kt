package com.example.feature_detail_screen.domain.model

import com.example.feature_core.ui.DisplayableItem

data class ActorDomain(
    val asCharacter: String,
    val id: String,
    val image: String,
    val name: String
) : DisplayableItem {
    override val itemId: String
        get() = id
}
