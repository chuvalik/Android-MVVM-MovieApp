package com.example.feature_detail_screen.domain.model

import com.google.gson.annotations.SerializedName

data class CastDomain(
    @SerializedName("character_name")
    val characterName: String,
    val id: String,
    val name: String,
    val picture: String
)