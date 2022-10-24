package com.example.structure.uibase.extend

import android.content.Context
import androidx.annotation.PluralsRes

interface StringRes {
    fun getString(@androidx.annotation.StringRes id: Int): String

    fun getString(@androidx.annotation.StringRes id: Int, vararg args: Any?): String

    fun getQuantityString(@PluralsRes id: Int, quantity: Int, vararg args: Any?): String
}

class StringResImpl(private val context: Context) : StringRes {
    override fun getString(id: Int): String {
        return context.resources.getString(id)
    }

    override fun getString(id: Int, vararg args: Any?): String {
        return context.resources.getString(id, *args)
    }

    override fun getQuantityString(
        @androidx.annotation.PluralsRes id: Int,
        quantity: Int,
        vararg args: Any?
    ): String {
        return context.resources.getQuantityString(id, quantity, *args)
    }
}
