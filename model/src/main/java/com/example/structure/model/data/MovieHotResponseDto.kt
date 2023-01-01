package com.example.structure.model.data

import com.google.gson.annotations.SerializedName

data class MovieHotResponseDto(
    @SerializedName("page")
    val page : Int,
    val results: List<HotMenuDto>,
    @SerializedName("total_pages")
    val totalPage : Int,
)
