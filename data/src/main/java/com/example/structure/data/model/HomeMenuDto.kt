package com.example.structure.data.model

import com.google.gson.annotations.SerializedName

data class HomeMenuDto(
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("poster_path")
    val imageUrl: String
)

data class HotMenuDto(
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("poster_path")
    val imageUrl: String
)


data class NewMenuDto(
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("poster_path")
    val imageUrl: String
)
