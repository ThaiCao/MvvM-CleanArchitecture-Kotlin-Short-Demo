package com.example.mydemo.remote.di

import com.example.mydemo.remote.apiservices.IMovieServiceApi
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceNetworkModule = module {
    single { provideMovieService(retrofit = get()) }
}

private fun provideMovieService(retrofit: Retrofit): IMovieServiceApi {
    return retrofit.create(IMovieServiceApi::class.java)
}