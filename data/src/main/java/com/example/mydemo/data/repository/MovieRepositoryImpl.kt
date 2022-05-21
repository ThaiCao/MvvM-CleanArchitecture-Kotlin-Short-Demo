package com.example.mydemo.data.repository

import com.example.mydemo.data.factory.IMovieDataStoreFactory
import com.example.mydemo.data.mapper.movie.MovieMapper
import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.domain.repositories.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieMapper: MovieMapper,
    private val movieDataStoreFactory: IMovieDataStoreFactory
) : IMovieRepository {
    override suspend fun getPopularMovies(): Flow<List<Movie>> {
        val movies = movieDataStoreFactory.getDataStore().getPopularsMovies()
        movieDataStoreFactory.getLocalDataStore().saveMovies(movies)
        movieDataStoreFactory.getMovieSharedPreferenceDataStore().saveMovieCacheExpiredTime(System.currentTimeMillis())
        return flow{
            emit(
                movies.map{movieMapper.mapFromEntity(it)}
            )
        }
    }
}