package com.example.structure.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.structure.error.ErrorMessageHandlerImpl
import com.example.structure.feature.internetconnection.InternetConnectionHelper
import com.example.structure.feature.internetconnection.InternetConnectionHelperImpl
import com.example.structure.presentation.error.ErrorMessageHandler
import com.example.structure.uibase.extend.StringRes
import com.example.structure.uibase.extend.StringResImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<StringRes> { StringResImpl(context = androidContext()) }

    factory<ErrorMessageHandler> { ErrorMessageHandlerImpl(stringRes = get()) }

    factory<InternetConnectionHelper> {
        InternetConnectionHelperImpl(get())
    }
}

fun provideSharePreferences(context: Context, fileName: String): SharedPreferences {
    return context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
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
