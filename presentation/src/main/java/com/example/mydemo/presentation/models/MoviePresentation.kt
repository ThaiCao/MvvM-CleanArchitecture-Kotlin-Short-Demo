package com.example.mydemo.presentation.models

data class MoviePresentation (
    var name: String? = "",
    var id: Long = 0,
    var posterPath: String? = "",
    var title: String? = "",
    var profilePath: String? = "",
    var voteAverage: Double? = 0.0,
)