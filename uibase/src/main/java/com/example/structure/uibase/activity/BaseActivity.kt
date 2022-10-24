package com.example.structure.uibase.activity

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.structure.language.localiza.LocalizationActivityDelegate
import com.example.structure.language.localiza.LocalizationActivityDelegateImpl
import com.example.structure.language.localiza.LocalizationActivityDelegateOwner

abstract class BaseActivity(contentLayoutId: Int = 0) :
    AppCompatActivity(contentLayoutId),
    LocalizationActivityDelegateOwner {

    override val localizationDelegate: LocalizationActivityDelegate by lazy {
        LocalizationActivityDelegateImpl(this, this::class.java)
    }

    override fun attachBaseContext(newBase: Context) {
        applyOverrideConfiguration(localizationDelegate.updateConfigurationLocale(newBase))
        super.attachBaseContext(newBase)
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }

    override fun getResources(): Resources {
        return localizationDelegate.getResources(super.getResources())
    }

    override fun onResume() {
        super.onResume()
        localizationDelegate.onResume(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        localizationDelegate.onCreate()
        super.onCreate(savedInstanceState)
    }
}
