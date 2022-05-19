package com.example.mydemo.data.factory

import com.example.mydemo.data.datasource.movie.IMovieDataStore

interface IMovieDataStoreFactory {
    suspend fun getDataStore(): IMovieDataStore
}