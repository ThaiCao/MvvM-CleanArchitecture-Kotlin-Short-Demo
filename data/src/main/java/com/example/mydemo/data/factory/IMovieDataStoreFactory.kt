package com.example.mydemo.data.factory

import com.example.mydemo.data.datasource.movie.IMovieDataStore
import com.example.mydemo.data.datasource.movie.local.sharedpreference.MovieSharedPreferenceDataStore

interface IMovieDataStoreFactory {
    suspend fun getDataStore(): IMovieDataStore
    suspend fun getLocalDataStore(): IMovieDataStore
    suspend fun getMovieSharedPreferenceDataStore(): MovieSharedPreferenceDataStore
}