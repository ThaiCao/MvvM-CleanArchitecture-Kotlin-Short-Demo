package com.example.structure.language.localiza

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import android.util.DisplayMetrics
import java.util.*

class LocalizationContext(
    context: Context
) : ContextWrapper(context) {

    @Suppress("DEPRECATION")
    override fun getResources(): Resources {
        val locale = Locale.getDefault()
        val configuration = super.getResources().configuration

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> configuration.setLocales(
                LocaleList(locale)
            )
            else -> configuration.setLocale(locale)
        }
        val metrics: DisplayMetrics = super.getResources().displayMetrics

        return Resources(assets, metrics, configuration)
    }
}
