package com.example.structure.data.model

import com.google.gson.annotations.SerializedName

data class MovieNewResponseDto(
    val page : Int,
    val results: List<NewMenuDto>,
    @SerializedName("total_pages")
    val totalPage : Int,
)
