package com.example.structure.mapper.di

import com.example.structure.mapper.feature.home.HomeMapper
import com.example.structure.mapper.feature.home.HomeMapperImpl
import org.koin.dsl.module

val mapperModule = module {

    factory<HomeMapper> { HomeMapperImpl() }

}
