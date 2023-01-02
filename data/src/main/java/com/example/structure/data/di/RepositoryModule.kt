package com.example.structure.data.di

import com.example.structure.data.feature.home.HomeRepositoryImpl
import com.example.structure.domain.feature.home.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<HomeRepository> {
        HomeRepositoryImpl(
            homeService = get(),
            homeMapper = get(),
        )
    }
}
