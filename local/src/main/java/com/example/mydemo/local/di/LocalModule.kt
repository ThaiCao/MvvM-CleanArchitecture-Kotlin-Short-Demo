package com.example.mydemo.local.di

import com.example.mydemo.data.datasource.movie.local.db.IMovieLocalStore
import com.example.mydemo.data.datasource.movie.local.sharedpreference.MovieSharedPreferenceDataStore
import com.example.mydemo.local.datasource.MovieLocalImpl
import com.example.mydemo.local.db.AppDatabase
import com.example.mydemo.local.mapper.movie.MovieLocalMapper
import com.example.mydemo.local.sharedPreferences.SharedPreferenceConst
import com.example.mydemo.local.sharedPreferences.movie.MovieSharedPreferenceDataStoreImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val localModule = module {

    //db
    single { AppDatabase.buildDatabase(androidApplication())}
    factory { (get() as AppDatabase).movieLocalDao() }

    factory<IMovieLocalStore>{
        MovieLocalImpl(
            movieLocalDao = get(),
            movieLocalMapper = get(),
        )
    }

    // sharedpreference
    single<MovieSharedPreferenceDataStore> {
        MovieSharedPreferenceDataStoreImpl(
            prefs = get(named(SharedPreferenceConst.PREF_EXPIRED_TIME))
        )
    }

    //for mapper
    factory { MovieLocalMapper() }


}
