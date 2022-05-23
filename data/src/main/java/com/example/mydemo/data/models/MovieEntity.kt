package com.example.mydemo.data.models

data class MovieEntity(
    var id: Long = 0,
    var title: String? = "",
    var name: String? = "",
    var voteAverage: Double? = 0.0,
    var profilePath: String? = "",
    var posterPath: String? = "",
)