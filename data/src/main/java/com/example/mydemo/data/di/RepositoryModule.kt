package com.example.mydemo.data.di

import com.example.mydemo.data.datasource.movie.local.db.MovieLocalDataStore
import com.example.mydemo.data.datasource.movie.remote.MovieRemoteDalocaltaStore
import com.example.mydemo.data.factory.IMovieDataStoreFactory
import com.example.mydemo.data.factory.MovieDataStoreFactoryImpl
import com.example.mydemo.data.mapper.movie.MovieMapper
import com.example.mydemo.data.repository.MovieRepositoryImpl
import com.example.mydemo.domain.repositories.IMovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory{
        MovieLocalDataStore(
            movieLocal = get(),
            movieSharedPreference = get(),
        )
    }
    factory{
        MovieRemoteDalocaltaStore(
            movieRemote = get(),
        )
    }

    factory<IMovieDataStoreFactory>{
        MovieDataStoreFactoryImpl(
            movieLocal = get(),
            movieSharedPreference = get(),
            movieLocalDataStore = get(),
            movieRemoteDataStore = get()
        )
    }

    factory<IMovieRepository>{
        MovieRepositoryImpl(
            movieMapper = get(),
            movieDataStoreFactory = get(),
        )
    }


    //for mapper
    factory { MovieMapper() }
}
