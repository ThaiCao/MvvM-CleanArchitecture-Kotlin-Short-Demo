package com.example.mydemo.factory

//import com.example.mydemo.domain.di.useCaseModule
import com.example.mydemo.local.di.localModule
import com.example.mydemo.remote.di.remoteModule
import org.koin.core.module.Module

interface ProviderModuleFactory{
    fun get(): List<Module>
}

class ProviderModuleFactoryImpl : ProviderModuleFactory{
    override fun get(): List<Module> {
        return listOf(
//            useCaseModule,
            localModule,
            remoteModule,
        )
    }

}