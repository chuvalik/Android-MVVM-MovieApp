package com.example.feature_search_screen.domain.model

import com.example.feature_core.ui.DisplayableItem

data class MovieDomain(
    val description: String,
    val id: String,
    val image: String,
    val resultType: String,
    val title: String
) : DisplayableItem {
    override val itemId: String
        get() = id
}