package com.example.structure.language.localiza

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Handler
import android.os.LocaleList
import android.os.Looper
import java.util.*
import kotlin.system.exitProcess

interface LocalizationActivityDelegate {

    fun addOnLocaleChangedListener(onLocaleChangedListener: OnLocaleChangedListener)

    fun removeOnLocaleChangedListener(onLocaleChangedListener: OnLocaleChangedListener)

    fun onCreate()

    // If activity is run to back stack. So we have to check if this activity is resume working.
    fun onResume(context: Context)

    fun attachBaseContext(context: Context): Context

    fun updateConfigurationLocale(context: Context): Configuration

    fun getApplicationContext(applicationContext: Context): Context

    fun getResources(resources: Resources): Resources

    // Provide method to set application language by country name.
    fun setLanguage(context: Context, newLanguage: String)

    fun setLanguage(context: Context, newLanguage: String, newCountry: String)

    fun setLanguage(context: Context, newLocale: Locale, isRestartApp: Boolean = true)
}

interface LocalizationActivityDelegateOwner {
    val localizationDelegate: LocalizationActivityDelegate
}

open class LocalizationActivityDelegateImpl(private val activity: Activity) :
    LocalizationActivityDelegate {
    private var isLocalizationChanged = false
    private lateinit var currentLanguage: Locale
    private val localeChangedListeners = ArrayList<OnLocaleChangedListener>()

    companion object {

        private const val KEY_ACTIVITY_LOCALE_CHANGED = "activity_locale_changed"

        private const val KEY_MAIN_PROCESS_PID = "phoenix_main_process_pid"

        private const val KEY_RESTART_INTENTS = "phoenix_restart_intents"
    }

    override fun addOnLocaleChangedListener(onLocaleChangedListener: OnLocaleChangedListener) {
        localeChangedListeners.add(onLocaleChangedListener)
    }

    override fun removeOnLocaleChangedListener(onLocaleChangedListener: OnLocaleChangedListener) {
        localeChangedListeners.remove(onLocaleChangedListener)
    }

    override fun onCreate() {
        setupLanguage()
        checkBeforeLocaleChanging()
    }

    // If activity is run to back stack. So we have to check if this activity is resume working.
    override fun onResume(context: Context) {
        Handler(Looper.getMainLooper()).post {
            checkLocaleChange(context)
            checkAfterLocaleChanging()
        }
    }

    override fun attachBaseContext(context: Context): Context {
        val locale = getLanguageSetting(context).getLanguageWithDefault(
            context,
            getLanguageSetting(context).getDefaultLanguage(context)
        )
        val config = context.resources.configuration

        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                config.setLocale(locale)
                val localeList = LocaleList(locale)
                LocaleList.setDefault(localeList)
                config.setLocales(localeList)
                context.createConfigurationContext(config)
            }
            else -> {
                config.setLocale(locale)
                context.createConfigurationContext(config)
            }
        }
    }

    override fun updateConfigurationLocale(context: Context): Configuration {
        val locale = Locale.getDefault()
        val config = context.resources.configuration
        return config.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                config.setLocale(locale)
                val localeList = LocaleList(locale)
                LocaleList.setDefault(localeList)
                config.setLocales(localeList)
            } else {
                config.setLocale(locale)
            }
        }
    }

    override fun getApplicationContext(applicationContext: Context): Context {
        return getLocaleManager().applyLocalizationContext(applicationContext)
    }

    override fun getResources(resources: Resources): Resources {
        val locale = Locale.getDefault()
        val config = resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            config.setLocale(locale)
            val localeList = LocaleList(locale)
            LocaleList.setDefault(localeList)
            config.setLocales(localeList)
        } else {
            config.setLocale(locale)
            config.setLayoutDirection(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
        }

        return resources
    }

    // Provide method to set application language by country name.
    override fun setLanguage(context: Context, newLanguage: String) {
        val locale = Locale(newLanguage)
        setLanguage(context, locale)
    }

    override fun setLanguage(context: Context, newLanguage: String, newCountry: String) {
        val locale = Locale(newLanguage, newCountry)
        setLanguage(context, locale)
    }

    override fun setLanguage(context: Context, newLocale: Locale, isRestartApp: Boolean) {
        if (!isCurrentLanguageSetting(newLocale, currentLanguage)) {
            currentLanguage = newLocale
            getLanguageSetting(context).setLanguage(activity, newLocale)
            notifyLanguageChanged(isRestartApp = isRestartApp)
        }
    }

    // Check that bundle come from locale change.
    // If yes, bundle will be remove and set boolean flag to "true".
    private fun checkBeforeLocaleChanging() {
        val isLocalizationChanged =
            activity.intent?.getBooleanExtra(KEY_ACTIVITY_LOCALE_CHANGED, false) ?: false
        if (isLocalizationChanged) {
            this.isLocalizationChanged = true
            activity.intent?.removeExtra(KEY_ACTIVITY_LOCALE_CHANGED)
        }
    }

    // Setup language to locale and language preference.
    // This method will called before onCreate.
    private fun setupLanguage() {
        currentLanguage = Locale.getDefault()
        checkLocaleChange(activity)
    }

    // Avoid duplicated setup
    private fun isCurrentLanguageSetting(newLocale: Locale, currentLocale: Locale): Boolean {
        return newLocale.toString() == currentLocale.toString()
    }

    // Let's take it change! (Using recreate method that available on API 11 or more.
    private fun notifyLanguageChanged(isRestartApp: Boolean = true) {
        sendOnBeforeLocaleChangedEvent()

        if (isRestartApp) {
            val intent = Intent(activity, MainActivity::class.java)
            val restartIntent = Intent.makeRestartActivityTask(intent.component)
            activity.startActivity(restartIntent)
            exitProcess(0)
        } else {
            if (activity.intent == null) activity.intent = Intent()
            activity.intent.putExtra(KEY_ACTIVITY_LOCALE_CHANGED, true)
            activity.recreate()
        }
    }

    // Check if locale has change while this activity was run to back stack.
    private fun checkLocaleChange(context: Context) {
        val oldLanguage = getLanguageSetting(context).getLanguageWithDefault(
            context,
            Locale.getDefault()
        )
        if (!isCurrentLanguageSetting(currentLanguage, oldLanguage)) {
            isLocalizationChanged = true
            notifyLanguageChanged()
        }
    }

    // Call override method if local is really changed
    private fun checkAfterLocaleChanging() {
        if (isLocalizationChanged) {
            sendOnAfterLocaleChangedEvent()
            isLocalizationChanged = false
        }
    }

    private fun sendOnBeforeLocaleChangedEvent() {
        for (changedListener in localeChangedListeners) {
            changedListener.onBeforeLocaleChanged()
        }
    }

    private fun sendOnAfterLocaleChangedEvent() {
        for (listener in localeChangedListeners) {
            listener.onAfterLocaleChanged()
        }
    }

    private fun getLocaleManager(): LocaleManager {
        return LocaleManagerImpl()
    }

    private fun getLanguageSetting(context: Context): LanguageSetting {
        return LanguageSettingIml(context = context)
    }
}
