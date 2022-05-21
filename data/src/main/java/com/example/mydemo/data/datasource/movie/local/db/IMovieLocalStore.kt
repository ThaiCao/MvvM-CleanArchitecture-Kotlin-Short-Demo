package com.example.mydemo.data.datasource.movie.local.db

import com.example.mydemo.data.models.MovieEntity

interface IMovieLocalStore {
    suspend fun getPopularsMovies(): List<MovieEntity>
    suspend fun saveMovies(listMovies: List<MovieEntity>)
    suspend fun isCached(): Boolean
    fun isExpired(lastUpdateTime: Long): Boolean
}