package com.example.structure.model.data

import com.google.gson.annotations.SerializedName

data class MovieMenuResponseDto(
    val page : Int = 0,
    val results: List<HomeMenuDto> ? = null,
    @SerializedName("total_pages")
    val totalPage : Int = 0,
)
