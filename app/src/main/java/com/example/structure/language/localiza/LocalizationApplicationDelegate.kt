package com.example.structure.language.localiza

import android.content.Context
import java.util.*

interface LocalizationApplicationDelegate {

    fun onConfigurationChanged(context: Context): Context

    fun attachBaseContext(context: Context): Context

    fun getApplicationContext(applicationContext: Context): Context

    fun init(context: Context, locale: Locale)

    fun getDefaultLanguage(context: Context, language: String): String

    fun getDefaultCountry(context: Context): String
}

class LocalizationApplicationDelegateImpl : LocalizationApplicationDelegate {
    override fun onConfigurationChanged(context: Context) =
        getLocaleManager().applyLocalizationContext(context)

    override fun attachBaseContext(context: Context): Context =
        getLocaleManager().applyLocalizationContext(context)

    override fun getApplicationContext(applicationContext: Context): Context =
        getLocaleManager().applyLocalizationContext(applicationContext)

    override fun init(context: Context, locale: Locale) {
        getLanguageSetting(context).setDefaultLanguage(context, locale)
    }

    override fun getDefaultLanguage(context: Context, language: String): String {
        return getLanguageSetting(context).getLanguage(context)?.language ?: language
    }

    private fun getLocaleManager(): LocaleManager {
        return LocaleManagerImpl()
    }

    override fun getDefaultCountry(context: Context): String {
        return getLanguageSetting(context).getLanguage(context)!!.country
    }

    private fun getLanguageSetting(context: Context): LanguageSetting {
        return LanguageSettingIml(context = context)
    }
}
