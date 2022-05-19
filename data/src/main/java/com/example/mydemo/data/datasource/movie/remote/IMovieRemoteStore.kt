package com.example.mydemo.data.datasource.movie.remote

import com.example.mydemo.data.models.MovieEntity

interface IMovieRemoteStore {
    suspend fun getPopularsMovies(): List<MovieEntity>
}