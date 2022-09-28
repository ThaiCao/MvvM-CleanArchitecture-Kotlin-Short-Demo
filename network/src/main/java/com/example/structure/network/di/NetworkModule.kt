package com.example.structure.network.di

import com.example.structure.network.feature.home.HomeService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
//    single<HomeService> { HomeServiceImpl() }

    single { provideHomeService(retrofit = get()) }
}

fun provideHomeService(retrofit: Retrofit): HomeService {
    return retrofit.create(HomeService::class.java)
}
