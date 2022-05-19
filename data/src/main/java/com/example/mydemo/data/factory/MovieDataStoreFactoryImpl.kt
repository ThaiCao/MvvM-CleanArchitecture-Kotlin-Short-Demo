package com.example.mydemo.data.factory

import com.example.mydemo.data.datasource.movie.IMovieDataStore
import com.example.mydemo.data.datasource.movie.local.IMovieLocalStore
import com.example.mydemo.data.datasource.movie.local.MovieLocalDataStore
import com.example.mydemo.data.datasource.movie.remote.MovieRemoteDataStore

class MovieDataStoreFactoryImpl(
    private val movieLocal: IMovieLocalStore,
    private val movieLocalDataStore: MovieLocalDataStore,
    private val movieRemoteDataStore: MovieRemoteDataStore,
    ) : IMovieDataStoreFactory {

    override suspend fun getDataStore(): IMovieDataStore {
        val isCache1 = movieLocal.isCached()
        return if(isCache1 && !movieLocal.isExpired()){
            movieLocalDataStore
        }else{
            movieRemoteDataStore
        }
    }

}