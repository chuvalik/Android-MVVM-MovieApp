package com.example.feature_main_screen.domain.model

import com.example.feature_core.ui.DisplayableItem

data class NewMovieDomain(
    val genre: String,
    val id: String,
    val picture: String,
    val rating: String,
    val title: String
) : DisplayableItem {
    override val itemId: Int = id.toInt()
}
