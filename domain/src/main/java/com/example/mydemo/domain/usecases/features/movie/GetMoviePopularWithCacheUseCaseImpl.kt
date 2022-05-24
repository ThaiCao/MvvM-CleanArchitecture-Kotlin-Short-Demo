package com.example.mydemo.domain.usecases.features.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mydemo.domain.models.Resource
import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.domain.repositories.IMovieRepository

class GetMoviePopularWithCacheUseCaseImpl(
    private val movieRepository: IMovieRepository,
) : IGetMoviePopularWithCacheUseCase{
    override suspend fun invoke(params: Boolean): LiveData<Resource<List<Movie>>> {
        return Transformations.map(movieRepository.getPopularMoviesWithCache(params)){it}
    }

}
