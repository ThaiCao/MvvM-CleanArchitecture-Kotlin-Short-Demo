package com.example.mydemo.factory

import com.example.mydemo.data.datasource.di.repositoryModule
import com.example.mydemo.di.appModule
import com.example.mydemo.di.navigatorModule
import com.example.mydemo.domain.di.useCaseModule
import com.example.mydemo.local.di.localModule
import com.example.mydemo.presentation.di.viewModelModule
import com.example.mydemo.remote.di.remoteModule
import com.example.mydemo.remote.di.retrofitModule
import com.example.mydemo.remote.di.serviceNetworkModule
import org.koin.core.module.Module

interface ProviderModuleFactory{
    fun get(): List<Module>
}

class ProviderModuleFactoryImpl : ProviderModuleFactory{
    override fun get(): List<Module> {
        return listOf(
            repositoryModule,
            useCaseModule,
            localModule,
            remoteModule,
            appModule,
            retrofitModule,
            serviceNetworkModule,
            viewModelModule,
            navigatorModule,
        )
    }

}