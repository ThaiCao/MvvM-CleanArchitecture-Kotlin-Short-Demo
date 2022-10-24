package com.example.structure.uibase.extend

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

fun getTintDrawable(
    context: Context,
    @DrawableRes drawableRes: Int,
    @AttrRes attrColorRes: Int
): Drawable? {

    ContextCompat.getDrawable(context, drawableRes)?.let {
        val drawable = DrawableCompat.wrap(it).mutate()
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_ATOP)
        DrawableCompat.setTint(drawable, getThemeColor(context, attrColorRes))
        return drawable
    }
    return null
}
