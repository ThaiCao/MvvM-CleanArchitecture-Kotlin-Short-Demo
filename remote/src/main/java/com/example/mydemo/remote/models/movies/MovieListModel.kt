package com.example.mydemo.remote.models.movies

import com.google.gson.annotations.SerializedName

data class MovieListModel(

    @SerializedName("page")
    var page: Int = 0,

    @SerializedName("results")
    var listOfMoviesResponse: List<MovieModel> = arrayListOf(),

    @SerializedName("total_pages")
    var totalPages: Int = 0,

    @SerializedName("total_results")
    var totalResults: Int = 0
)
