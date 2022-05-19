package com.example.mydemo.data.datasource.movie.local

import com.example.mydemo.data.datasource.movie.IMovieDataStore
import com.example.mydemo.data.models.MovieEntity
import kotlinx.coroutines.flow.Flow

class MovieLocalDataStore(
    private val movieLocal: IMovieLocalStore
) : IMovieDataStore {
    override suspend fun getPopularsMovies(): List<MovieEntity> {
        return movieLocal.getPopularsMovies()
    }

    override suspend fun saveMovies(listMovies: List<MovieEntity>) {
       return movieLocal.saveMovies(listMovies)
    }
}