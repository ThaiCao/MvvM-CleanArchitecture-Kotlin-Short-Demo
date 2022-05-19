package com.example.mydemo.di

import com.example.mydemo.features.popularmovie.PopularMovieNavigator
import com.example.mydemo.features.popularmovie.PopularMovieNavigatorImpl
import org.koin.dsl.module

val navigatorModule = module {
    single<PopularMovieNavigator> { PopularMovieNavigatorImpl() }
}
