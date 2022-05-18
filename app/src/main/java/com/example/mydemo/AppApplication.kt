package com.example.mydemo

import android.app.Application
import com.example.mydemo.factory.ProviderModuleFactoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            modules(ProviderModuleFactoryImpl().get())
        }
    }
}