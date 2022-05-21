package com.example.mydemo.data.factory

import com.example.mydemo.data.datasource.movie.IMovieDataStore
import com.example.mydemo.data.datasource.movie.local.db.IMovieLocalStore
import com.example.mydemo.data.datasource.movie.local.db.MovieLocalDataStore
import com.example.mydemo.data.datasource.movie.local.sharedpreference.MovieSharedPreferenceDataStore
import com.example.mydemo.data.datasource.movie.remote.MovieRemoteDataStore

class MovieDataStoreFactoryImpl(
    private val movieLocal: IMovieLocalStore,
    private val movieSharedPreference: MovieSharedPreferenceDataStore,
    private val movieLocalDataStore: MovieLocalDataStore,
    private val movieRemoteDataStore: MovieRemoteDataStore,
    ) : IMovieDataStoreFactory {

    override suspend fun getDataStore(): IMovieDataStore {
        val isCache = movieLocal.isCached()
        val lastUpdateTime = movieSharedPreference.getMovieCacheExpiredTime()?.let{it}?:run { 0 }
        return if(isCache && !movieLocal.isExpired(lastUpdateTime)){
            movieLocalDataStore
        }else{
            movieRemoteDataStore
        }
    }

    override suspend fun getLocalDataStore(): IMovieDataStore {
        return movieLocalDataStore
    }

    override suspend fun getMovieSharedPreferenceDataStore(): MovieSharedPreferenceDataStore {
        return movieSharedPreference
    }

}