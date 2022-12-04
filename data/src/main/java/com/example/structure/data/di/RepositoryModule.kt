package com.example.structure.data.di

import com.example.structure.data.feature.home.HomeMapper
import com.example.structure.data.feature.home.HomeMapperImpl
import com.example.structure.data.feature.home.HomeRepositoryImpl
import com.example.structure.domain.feature.home.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<HomeMapper> { HomeMapperImpl() }

    factory<HomeRepository> {
        HomeRepositoryImpl(
            homeService = get(),
            homeMapper = get(),
        )
    }
}
