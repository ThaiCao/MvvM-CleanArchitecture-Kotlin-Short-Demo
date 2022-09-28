package com.example.structure.language.di

import android.content.Context
import android.content.SharedPreferences

fun provideSharePreferences(context: Context, fileName: String): SharedPreferences {
    return context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
}
