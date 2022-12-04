package com.example.structure.presentation.di

import com.example.structure.presentation.feature.home.HomeViewModel
import com.example.structure.presentation.feature.splash.SplashViewModel
import com.example.structure.presentation.mapper.HomeMenuUiMapper
import com.example.structure.presentation.mapper.HomeMenuUiMapperImpl
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {

    factory<HomeMenuUiMapper> { HomeMenuUiMapperImpl() }

    viewModel {
        HomeViewModel(
            getHomeHotUseCase = get(),
            getHomeNewUseCase = get(),
            getHomeMenuUseCase = get(),
            errorMessageHandler = get(),
            homeMenuUiMapper = get()
        )
    }

    viewModel {
        SplashViewModel()
    }
}
