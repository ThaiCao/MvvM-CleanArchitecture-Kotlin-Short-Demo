package com.example.structure.language

object LanguageManager {
    private var languageProvider = DefaultLanguageProviderImpl()

    fun getLanguages(): List<Language> {
        return languageProvider.getLanguages()
    }

    fun getDefault() = languageProvider.getDefault()

    fun find(language: Language): Language {
        val languages = getLanguages()
        return languages.find { it.isSame(language) }
            ?: languages.first()
    }
}
