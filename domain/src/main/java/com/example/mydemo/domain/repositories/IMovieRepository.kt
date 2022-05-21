package com.example.mydemo.domain.repositories

import com.example.mydemo.domain.models.movies.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getPopularMovies(): Flow<List<Movie>>
}