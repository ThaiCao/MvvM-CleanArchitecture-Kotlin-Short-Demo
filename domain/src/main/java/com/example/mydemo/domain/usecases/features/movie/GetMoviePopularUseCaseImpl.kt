package com.example.mydemo.domain.usecases.features.movie

import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.domain.repositories.IMovieRepository
import com.example.mydemo.domain.usecases.base.IFlowUseCase
import kotlinx.coroutines.flow.Flow

class GetMoviePopularUseCaseImpl(
    private val movieRepository: IMovieRepository,
) : IGetMoviePopularUseCase{
    override suspend fun invoke(params: IFlowUseCase.None): Flow<List<Movie>> {
        return movieRepository.getPopularMovies()
    }

}