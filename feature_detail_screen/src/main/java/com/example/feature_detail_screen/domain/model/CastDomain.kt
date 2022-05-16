package com.example.feature_detail_screen.domain.model

import com.example.feature_core.ui.DisplayableItem
import com.google.gson.annotations.SerializedName

data class CastDomain(
    @SerializedName("character_name")
    val characterName: String,
    val id: String,
    val name: String,
    val picture: String
) : DisplayableItem {
    override val itemId: Int = id.toInt()
}