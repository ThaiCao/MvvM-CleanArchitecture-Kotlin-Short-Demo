package com.example.mydemo.data.repository

import com.example.mydemo.data.mapper.movie.MovieMapper
import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.domain.repositories.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieMapper: MovieMapper,
) : IMovieRepository {
    override fun getPopularMovies(): Flow<Result<Movie>> {
        TODO("Not yet implemented")
    }

    override fun saveMovies(listMovies: List<Movie>): Flow<Result<Boolean>> {
        TODO("Not yet implemented")
    }
}