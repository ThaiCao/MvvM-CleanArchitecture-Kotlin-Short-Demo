package com.example.mydemo.domain.usecases.features.movie

import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.domain.usecases.base.IUseCase

interface IGetMoviePopularUseCase : IUseCase<List<Movie>, IUseCase.None>