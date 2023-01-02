package com.example.structure.factory

import com.example.structure.data.di.apiServiceModule
import com.example.structure.data.di.managerModule
import com.example.structure.data.di.repositoryModule
import com.example.structure.di.appModule
import com.example.structure.di.navigatorModule
import com.example.structure.di.networkModule
import com.example.structure.domain.di.useCaseModule
import com.example.structure.mapper.di.mapperModule
import com.example.structure.presentation.di.viewModelModule
import org.koin.core.module.Module

interface ProviderModulesFactory {
    fun get(): List<Module>
}

class ProviderModulesFactoryImpl : ProviderModulesFactory {
    override fun get(): List<Module> {
        return listOf(
            // app module
            appModule,
            networkModule,
            navigatorModule,

            // data module
            apiServiceModule,
            managerModule,
            repositoryModule,

            // mapper
            mapperModule,

            //domain module
            useCaseModule,
            viewModelModule,
        )
    }

}
