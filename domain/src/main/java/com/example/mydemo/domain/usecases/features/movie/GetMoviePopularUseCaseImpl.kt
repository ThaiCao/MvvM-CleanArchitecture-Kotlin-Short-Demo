package com.example.mydemo.domain.usecases.features.movie

import com.example.mydemo.domain.dispatcher.DispatcherProvider
import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.domain.repositories.IMovieRepository
import com.example.mydemo.domain.usecases.base.IUseCase
import kotlinx.coroutines.flow.Flow

class GetMoviePopularUseCaseImpl(
    private val movieRepository: IMovieRepository,
    override val dispatcherProvider: DispatcherProvider
) : IGetMoviePopularUseCase{
    override fun run(params: IUseCase.None): Flow<Result<List<Movie>>> {
        TODO("Not yet implemented")
    }
}