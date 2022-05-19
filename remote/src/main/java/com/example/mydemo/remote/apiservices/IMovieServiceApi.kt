package com.example.mydemo.remote.apiservices

import com.example.mydemo.remote.models.movies.MovieListModel
import retrofit2.http.GET

interface IMovieServiceApi {
    @GET("movie/popular")
    suspend fun getPopularsMovies(): MovieListModel
}