package com.example.structure.uicommon.extend

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat

fun getThemeColor(
    context: Context,
    @AttrRes attrColorRes: Int
): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(attrColorRes, typedValue, true)
    return ContextCompat.getColor(context, typedValue.resourceId)
}
