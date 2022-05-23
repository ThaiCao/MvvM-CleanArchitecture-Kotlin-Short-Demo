package com.example.mydemo.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.mydemo.local.sharedPreferences.SharedPreferenceConst.PREF_APP
import com.example.mydemo.local.sharedPreferences.SharedPreferenceConst.PREF_EXPIRED_TIME
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single {
        provideEncryptedSharePreferences(
            context = androidContext(),
            fileName = PREF_APP
        )
    }

    single(named(PREF_EXPIRED_TIME)) {
        provideEncryptedSharePreferences(
            context = get(),
            fileName = PREF_EXPIRED_TIME
        )
    }
}

fun provideEncryptedSharePreferences(context: Context, fileName: String): SharedPreferences {
    val mainKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    return EncryptedSharedPreferences.create(
        context,
        fileName,
        mainKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}
