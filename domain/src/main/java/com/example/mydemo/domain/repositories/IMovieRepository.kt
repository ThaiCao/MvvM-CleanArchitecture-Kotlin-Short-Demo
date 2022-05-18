package com.example.mydemo.domain.repositories

import com.example.mydemo.domain.models.movies.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPopularMovies(): Flow<Result<Movie>>
    fun saveMovies(listMovies: List<Movie>): Flow<Result<Boolean>>
}