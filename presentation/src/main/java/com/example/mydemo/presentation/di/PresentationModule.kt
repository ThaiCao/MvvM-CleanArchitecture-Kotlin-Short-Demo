package com.example.mydemo.presentation.di

import com.example.mydemo.presentation.mapper.movie.MoviePresentationMapper
import com.example.mydemo.presentation.viewmodels.MovieDetailViewModel
import com.example.mydemo.presentation.viewmodels.movie.PopularMovieViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel {
        PopularMovieViewModel(get(), get())
    }

    viewModel {
        MovieDetailViewModel()
    }

    //for mapper
    factory { MoviePresentationMapper() }
}
