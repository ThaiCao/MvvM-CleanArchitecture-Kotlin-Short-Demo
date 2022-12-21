package com.example.structure.uibase.extend

import android.content.res.Resources

fun getScreenWidth(percent: Float = 1F): Int {
    return (Resources.getSystem().displayMetrics.widthPixels * percent).toInt()
}

fun getScreenHeight(percent: Float = 1F): Int {
    return (Resources.getSystem().displayMetrics.heightPixels * percent).toInt()
}
