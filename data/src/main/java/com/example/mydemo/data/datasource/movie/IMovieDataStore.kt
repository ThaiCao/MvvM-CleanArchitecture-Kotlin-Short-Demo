package com.example.mydemo.data.datasource.movie

import com.example.mydemo.data.models.MovieEntity

interface IMovieDataStore {
    suspend fun getPopularsMovies(): List<MovieEntity>
    suspend fun saveMovies(listMovies: List<MovieEntity>)
}