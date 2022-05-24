package com.example.mydemo.remote.apiservices

import com.example.mydemo.remote.models.movies.MovieListModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface IMovieServiceApi {
    @GET("movie/popular")
    suspend fun getPopularsMovies(): MovieListModel

    @GET("movie/popular")
    suspend fun getPopularsMoviesAsync(): MovieListModel
}
