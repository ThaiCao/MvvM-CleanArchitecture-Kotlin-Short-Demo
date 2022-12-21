package com.example.structure.presentation.util

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

suspend fun Context.loadIconAsBitmap(url: String?): Bitmap? {
    if (url.isNullOrEmpty()) return null
    return Glide.with(this).asBitmap().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .load(url)
        .submit().get()
}
