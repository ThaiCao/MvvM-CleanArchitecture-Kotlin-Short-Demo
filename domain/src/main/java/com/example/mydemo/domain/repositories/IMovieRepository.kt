package com.example.mydemo.domain.repositories

import com.example.mydemo.domain.models.Resource
import com.example.mydemo.domain.models.movies.Movie
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.LiveData

interface IMovieRepository {
    suspend fun getPopularMovies(): Flow<List<Movie>>
    suspend fun getPopularMoviesWithCache(forceRefresh: Boolean): LiveData<Resource<List<Movie>>>
}
