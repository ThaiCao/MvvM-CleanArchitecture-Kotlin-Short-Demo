package com.example.structure.uibase.extend

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.example.structure.uibase.R
import com.example.structure.uibase.GlideApp


fun ImageView.bindCenterCrop(url: String? = null) {
    try {
        scaleType = ImageView.ScaleType.CENTER_CROP
        GlideApp.with(this)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ImageView.bindImage(res: Int? = null) {
    try {
        if (res == null) {
            setImageResource(R.drawable.img_placeholder)
            scaleType = ImageView.ScaleType.CENTER_CROP
            return
        }
        GlideApp.with(this)
            .load(res)
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ImageView.bindImage(url: String? = null) {
    try {
        if (url.isNullOrBlank()) {
            setImageResource(R.drawable.img_placeholder)
            return
        }
        GlideApp.with(this)
            .load(url)
            .placeholder(R.drawable.img_placeholder)
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ImageView.bindImageWithoutPlaceHolder(url: String? = null) {
    try {
        GlideApp.with(this)
            .load(url)
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ImageView.bindImageWithPlaceHolder(url: String? = null, @DrawableRes placeHolder: Int? = null) {
    try {
        if (placeHolder == null) {
            bindImage(url = url)
        } else {
            if (url.isNullOrBlank()) {
                setImageResource(placeHolder)
                return
            }
            GlideApp.with(this)
                .load(url)
                .placeholder(placeHolder)
                .into(this)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ImageView.loadGif(@DrawableRes drawableRes: Int) {
    try {
        Glide.with(this)
            .load(drawableRes)
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun loadBitmapOrNull(context: Context, url: String? = null): Bitmap? {
    if (url.isNullOrEmpty()) return null
    return Glide.with(context).asBitmap()
        .load(url)
        .submit().get()
}
