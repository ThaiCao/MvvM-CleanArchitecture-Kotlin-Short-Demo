package com.example.structure.domain.di

import com.example.structure.domain.feature.home.*
import com.example.structure.domain.usecase.DispatcherProvider
import com.example.structure.domain.usecase.DispatcherProviderImpl
import org.koin.dsl.module

val useCaseModule = module {

    single<DispatcherProvider> { DispatcherProviderImpl() }

    factory<GetHomeHotUseCase> {
        GetHomeHotUseCaseImpl(
            homeRepository = get(),
            dispatcherProvider = get()
        )
    }

    factory<GetHomeNewUseCase> {
        GetHomeNewUseCaseImpl(
            homeRepository = get(),
            dispatcherProvider = get()
        )
    }

    factory<GetHomeMenuUseCase> {
        GetHomeMenuUseCaseImpl(
            homeRepository = get(),
            dispatcherProvider = get()
        )
    }
}
