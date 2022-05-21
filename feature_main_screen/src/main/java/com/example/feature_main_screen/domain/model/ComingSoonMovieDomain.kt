package com.example.feature_main_screen.domain.model

import com.example.feature_core.ui.DisplayableItem

data class ComingSoonMovieDomain(
    val id: String,
    val image: String,
    val releaseState: String,
    val fullTitle: String,
    val year: String
) : DisplayableItem {
    override val itemId: String
        get() = id
}