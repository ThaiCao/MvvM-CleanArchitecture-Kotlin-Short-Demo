package com.example.structure.uibase.extend

import android.content.res.Resources

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
