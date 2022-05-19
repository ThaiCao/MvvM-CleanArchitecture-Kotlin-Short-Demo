package com.example.mydemo.domain.usecases.features.movie

import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.domain.usecases.base.IFlowUseCase

interface IGetMoviePopularUseCase : IFlowUseCase<List<Movie>, IFlowUseCase.None>