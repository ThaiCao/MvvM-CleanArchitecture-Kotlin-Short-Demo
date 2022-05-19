package com.example.mydemo.utils.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import java.util.*

val View.isLayoutRtl
    get() = ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL

fun View.getString(@StringRes resId: Int) = resources.getString(resId)

fun View.getString(@StringRes resId: Int, vararg formatArgs: Any): String {
    return context.resources.getString(resId, *formatArgs)
}

fun View.show(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun View.showOrHide(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.INVISIBLE
}

fun ViewGroup.inflater(): LayoutInflater {
    return LayoutInflater.from(context)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun isLayoutDirectionRtl(): Boolean {
    return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL
}