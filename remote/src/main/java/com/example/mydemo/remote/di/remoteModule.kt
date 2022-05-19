package com.example.mydemo.remote.di

import com.example.mydemo.data.datasource.movie.remote.IMovieRemoteStore
import com.example.mydemo.remote.datasource.MovieRemoteImpl
import com.example.mydemo.remote.mapper.movie.MovieRemoteMapper
import org.koin.dsl.module

val remoteModule = module {
    factory<IMovieRemoteStore>{
        MovieRemoteImpl(
            moviesService = get(),
            movieRemoteMapper = get()
        )
    }

    //for mapper
    factory { MovieRemoteMapper() }
}
