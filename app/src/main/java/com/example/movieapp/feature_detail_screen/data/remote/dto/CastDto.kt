package com.example.movieapp.feature_detail_screen.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CastDto(
    @SerializedName("character_name")
    val characterName: String,
    val id: Int,
    val name: String,
    val picture: String
)