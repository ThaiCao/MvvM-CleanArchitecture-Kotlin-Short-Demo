package com.example.mydemo.utils.common

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.example.mydemo.R
import com.example.mydemo.GlideApp

fun ImageView.bindCenterCrop(url: String? = null) {
    scaleType = ImageView.ScaleType.CENTER_CROP
    GlideApp.with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.img_placeholder)
        .error(R.drawable.img_placeholder)
        .into(this)
}

fun ImageView.bindImage(res: Int? = null) {
    if (res == null) {
        setImageResource(R.drawable.img_placeholder)
        scaleType = ImageView.ScaleType.CENTER_CROP
        return
    }
    GlideApp.with(this)
        .load(res)
        .into(this)
}

fun ImageView.bindImage(url: String? = null) {
    if (url.isNullOrBlank()) {
        setImageResource(R.drawable.img_placeholder)
        return
    }
    GlideApp.with(this)
        .load(url)
        .placeholder(R.drawable.img_placeholder)
        .into(this)
}

fun ImageView.bindImageWithoutPlaceHolder(url: String? = null) {
    GlideApp.with(this)
        .load(url)
        .into(this)
}

fun ImageView.loadGif(@DrawableRes drawableRes: Int) {
    Glide.with(this)
        .load(drawableRes)
        .into(this)
}
