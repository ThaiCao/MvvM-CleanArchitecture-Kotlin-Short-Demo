package com.example.structure.language

import java.util.*

interface LanguageProvider {
    fun getDefault(): Language
    fun getLanguages(): List<Language>
}

class DefaultLanguageProviderImpl : LanguageProvider {
    override fun getDefault(): Language {
        return Language(
            languageName = "English(GB)",
            language = Locale.getDefault().toLanguage().language,
            country = Locale.getDefault().country
        )
    }

    override fun getLanguages(): List<Language> {
        return arrayListOf(
            Language(
                languageName = "English(GB)",
                language = Locale.UK.language,
                country = Locale.UK.country
            ),
        )
    }
}
