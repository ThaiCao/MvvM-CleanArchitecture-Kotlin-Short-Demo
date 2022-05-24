package com.example.mydemo.data.datasource.movie.remote

import com.example.mydemo.data.models.MovieEntity
import kotlinx.coroutines.Deferred

interface IMovieRemoteStore {
    suspend fun getPopularsMovies(): List<MovieEntity>
    suspend fun getPopularsMoviesAsync(): List<MovieEntity>
}
