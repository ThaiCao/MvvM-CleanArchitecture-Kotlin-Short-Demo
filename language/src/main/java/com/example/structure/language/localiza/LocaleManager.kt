package com.example.structure.language.localiza

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import java.util.*

interface LocaleManager {

    fun applyLocalizationContext(baseContext: Context): Context

    fun getLocaleFromConfiguration(configuration: Configuration): Locale
}

class LocaleManagerImpl : LocaleManager {

    override fun applyLocalizationContext(baseContext: Context): Context {
        val baseLocale = getLocaleFromConfiguration(baseContext.resources.configuration)
        val currentLocale = Locale.getDefault()
        if (!baseLocale.toString().equals(currentLocale.toString(), ignoreCase = true)) {
            return when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                    val context = LocalizationContext(
                        context = baseContext
                    )
                    val config = context.resources.configuration
                    config.setLocale(currentLocale)
                    val localeList = LocaleList(currentLocale)
                    LocaleList.setDefault(localeList)
                    config.setLocales(localeList)
                    context.createConfigurationContext(config)
                }
                else -> {
                    val context = LocalizationContext(
                        context = baseContext
                    )
                    val config = context.resources.configuration
                    config.setLocale(currentLocale)
                    context.createConfigurationContext(config)
                }
            }
        } else {
            return baseContext
        }
    }

    override fun getLocaleFromConfiguration(configuration: Configuration): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            configuration.locales.get(0)
        } else {
            configuration.locale
        }
    }
}
