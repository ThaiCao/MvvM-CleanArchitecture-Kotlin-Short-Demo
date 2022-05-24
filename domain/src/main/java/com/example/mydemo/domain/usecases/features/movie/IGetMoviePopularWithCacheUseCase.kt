package com.example.mydemo.domain.usecases.features.movie

import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.domain.usecases.base.ILiveDataUseCase

interface IGetMoviePopularWithCacheUseCase : ILiveDataUseCase<List<Movie>, Boolean>
