package com.example.mydemo.data.datasource.movie

import com.example.mydemo.data.models.MovieEntity
import kotlinx.coroutines.Deferred

interface IMovieDataStore {
    suspend fun getPopularsMovies(): List<MovieEntity>
    suspend fun getPopularsMoviesWithCache(): List<MovieEntity>
    suspend fun saveMovies(listMovies: List<MovieEntity>)
    fun saveMovieExpiredTime(expiredTime: Long)
    fun getMovieExpiredTime(): Long?
}
