package com.example.mydemo.remote.models.movies

import com.google.gson.annotations.SerializedName

class MovieModel {
    @SerializedName("name")
    var name: String = ""

    @SerializedName("id")
    var id: Long = 0

    @SerializedName("poster_path")
    var posterPath: String? = ""

    @SerializedName("title")
    var title: String? = ""

    @SerializedName("profile_path")
    var profilePath: String = ""

    @SerializedName("vote_average")
    var voteAverage: Double? = 0.0
}