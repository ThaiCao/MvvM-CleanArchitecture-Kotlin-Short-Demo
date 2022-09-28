package com.example.structure.language

import android.os.Parcelable
import java.util.*

@Parcelize
class Language(
    var languageName: String,
    var language: String,
    var country: String
) : Parcelable

fun Language.toLocale(): Locale {
    return Locale(language, country)
}

fun Locale.toLanguage(): Language {
    return Language(languageName = "", language = language, country = country)
}

fun Language.isDifferent(with: Language): Boolean {
    return !isSame(with)
}

fun Language.isSame(with: Language): Boolean {
    return language.equals(with.language, ignoreCase = true) &&
        country.equals(with.country, ignoreCase = true)
}
