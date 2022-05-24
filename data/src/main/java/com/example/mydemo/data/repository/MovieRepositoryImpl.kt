package com.example.mydemo.data.repository

import androidx.lifecycle.LiveData
import com.example.mydemo.data.factory.IMovieDataStoreFactory
import com.example.mydemo.data.mapper.movie.MovieMapper
import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.data.networkbound.NetworkBoundResource
import com.example.mydemo.domain.models.Resource
import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.domain.repositories.IMovieRepository
import kotlinx.coroutines.Deferred
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

    override suspend fun getPopularMoviesWithCache(forceRefresh: Boolean): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieEntity>>() {

            override fun processResponse(response: List<MovieEntity>): List<Movie>
                = response.map{movieMapper.mapFromEntity(it)}

            override suspend fun saveCallResults(items: List<Movie>)
                = movieDataStoreFactory.getLocalDataStore().saveMovies(items.map{movieMapper.mapToEntity(it)})

            override fun shouldFetch(data: List<Movie>?): Boolean
                = data == null || data.isEmpty() || forceRefresh

            override suspend fun loadFromDb(): List<Movie>
                = movieDataStoreFactory.getLocalDataStore().getPopularsMovies().map{movieMapper.mapFromEntity(it)}

            override suspend fun createCallAsync(): List<MovieEntity>
                = movieDataStoreFactory.getRemoteDataStore().getPopularsMoviesWithCache()

        }.build().asLiveData()
    }
}
