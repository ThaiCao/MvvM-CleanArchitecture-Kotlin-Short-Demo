package com.example.mydemo.local.di

import com.example.mydemo.data.datasource.movie.local.IMovieLocalStore
import com.example.mydemo.local.datasource.MovieLocalImpl
import com.example.mydemo.local.db.AppDatabase
import com.example.mydemo.local.mapper.movie.MovieLocalMapper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    single { AppDatabase.buildDatabase(androidApplication())}
    factory { (get() as AppDatabase).movieLocalDao() }

    factory<IMovieLocalStore>{
        MovieLocalImpl(
            movieLocalDao = get(),
            movieLocalMapper = get()
        )
    }

    //for mapper
    factory { MovieLocalMapper() }
}
