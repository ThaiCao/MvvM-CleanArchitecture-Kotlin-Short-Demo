package com.example.structure

import androidx.multidex.MultiDexApplication
import com.example.structure.factory.ProviderModulesFactoryImpl
import com.github.ajalt.timberkt.Timber
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class AppApplication : MultiDexApplication() {

override fun onCreate() {
    super.onCreate()
    /**
     * Cheating for UI testing
     * Don't know reason why when call ApplicationProvider.getApplicationContext()
     * Then onCreate() will many times and throw org.koin.core.error.KoinAppAlreadyStartedException: A Koin Application has already been started
     * So need to stopKoin()
     */
    stopKoin()

    if (BuildConfig.LOGGING) {
        Timber.plant(LoggerManager())
    }

    startKoin {
        androidLogger(Level.NONE)
        androidContext(this@AppApplication)
        modules(ProviderModulesFactoryImpl().get())
    }
}
}
