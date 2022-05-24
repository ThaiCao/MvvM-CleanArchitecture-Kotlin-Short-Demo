package com.example.mydemo.data.factory

import com.example.mydemo.data.datasource.movie.IMovieDataStore
import com.example.mydemo.data.datasource.movie.local.sharedpreference.MovieSharedPreferenceDataStore

interface IMovieDataStoreFactory {
    suspend fun getDataStore(): IMovieDataStore
    fun getLocalDataStore(): IMovieDataStore
    fun getRemoteDataStore(): IMovieDataStore
    suspend fun getMovieSharedPreferenceDataStore(): MovieSharedPreferenceDataStore
}
