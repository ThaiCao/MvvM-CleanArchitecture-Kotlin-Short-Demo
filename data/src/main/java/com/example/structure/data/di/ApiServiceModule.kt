package com.example.structure.data.di

import com.example.structure.data.feature.home.HomeService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiServiceModule = module {

    single { provideHomeService(retrofit = get()) }
}

private fun provideHomeService(retrofit: Retrofit): HomeService {
    return retrofit.create(HomeService::class.java)
}

object NameInjection {
    const val NAME_RETROFIT_FOR_CONFIG_SERVICE = "name_retrofit_for_config_service"
    const val NAME_RETROFIT_FOR_CUSTOMER_SERVICE = "name_retrofit_for_customer_service"
}
