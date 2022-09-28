package com.example.structure.language.localiza

import android.content.Context
import com.example.structure.language.Language
import com.example.structure.language.di.provideSharePreferences
import com.example.structure.language.toLanguage
import com.example.structure.language.toLocale
import java.util.*

interface LanguageSetting {

    fun setDefaultLanguage(context: Context, locale: Locale)

    fun getDefaultLanguage(context: Context): Locale

    fun setLanguage(context: Context, locale: Locale)

    fun getLanguage(context: Context): Locale?

    fun getLanguageWithDefault(context: Context, default: Locale): Locale

    fun clear()
}

class LanguageSettingIml(context: Context) : LanguageSetting {

    private var languagePref: Language? by provideSharePreferences(
        context,
        PREF_LANGUAGE
    ).reference(
        PREF_LANGUAGE_CODE
    )

    companion object {
        const val PREF_LANGUAGE_CODE = "pref_language_code"
    }

    override fun setDefaultLanguage(context: Context, locale: Locale) {
        when (val language = languagePref) {
            null -> {
                languagePref = locale.toLanguage()
                Locale.setDefault(locale)
            }
            else -> Locale.setDefault(language.toLocale())
        }
    }

    override fun getDefaultLanguage(context: Context): Locale {
        return languagePref?.toLocale() ?: Locale.getDefault()
    }

    override fun setLanguage(context: Context, locale: Locale) {
        languagePref = locale.toLanguage()
        Locale.setDefault(locale)
    }

    override fun getLanguage(context: Context): Locale? {
        return languagePref?.toLocale()
    }

    override fun getLanguageWithDefault(context: Context, default: Locale): Locale {
        return getLanguage(context) ?: run {
            setLanguage(context, default)
            default
        }
    }

    override fun clear() {
        languagePref = null
    }
}
