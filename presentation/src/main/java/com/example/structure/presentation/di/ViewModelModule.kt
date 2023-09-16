package com.example.structure.presentation.di

import com.example.structure.presentation.feature.home.HomeViewModel
import com.example.structure.presentation.feature.moviedetail.MovieDetailViewModel
import com.example.structure.presentation.feature.signin.SignInViewModel
import com.example.structure.presentation.feature.splash.SplashViewModel
import com.example.structure.presentation.mapper.HomeMapper
import com.example.structure.presentation.mapper.HomeMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            getHomeHotUseCase = get(),
            getHomeNewUseCase = get(),
            getHomeMenuUseCase = get(),
            errorMessageHandler = get(),
            homeMapper = get()
        )
    }

    viewModel {
        SplashViewModel()
    }

    viewModel {
        MovieDetailViewModel()
    }

    viewModel {
        SignInViewModel()
    }

    factory<HomeMapper> { HomeMapperImpl() }
}
