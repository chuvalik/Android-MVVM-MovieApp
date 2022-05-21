package com.example.feature_main_screen.domain.model

import com.example.feature_core.ui.DisplayableItem

data class TrendingMovieDomain(
    val fullTitle: String,
    val id: String,
    val imDbRating: String,
    val image: String,
) : DisplayableItem {
    override val itemId: String
        get() = id
}
