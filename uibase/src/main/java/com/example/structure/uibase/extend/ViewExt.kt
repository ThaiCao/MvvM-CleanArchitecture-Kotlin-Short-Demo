package com.example.structure.uibase.extend

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.annotation.AnimRes
import androidx.annotation.StringRes
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.updateLayoutParams
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

fun View.show(@AnimRes withAnimation: Int?) {
    this.show()
    withAnimation ?: return
    try {
        val animation = AnimationUtils.loadAnimation(context, withAnimation)
        animation.reset()
        this.clearAnimation()
        this.startAnimation(animation)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.isShow(): Boolean {
    return this.visibility == View.VISIBLE
}

fun isLayoutDirectionRtl(): Boolean {
    return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL
}

operator fun View.plus(view: View): List<View> {
    return arrayListOf(this, view)
}

fun <E : View> List<E>.moveUp(
    height: Float,
    delay: Int = 600,
    onEndListener: (() -> Unit)? = null
) {
    ValueAnimator.ofFloat(-height).apply {
        interpolator = DecelerateInterpolator(1.2f)
        startDelay = delay.toLong()
        duration = 1000
        addUpdateListener { value ->
            forEach {
                it.translationY = value.animatedValue as Float
            }
        }
        addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                removeListener(this)
                onEndListener?.invoke()
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationStart(p0: Animator?) {}
        })
        start()
    }
}

fun View.setMarginBottom(size: Int) {
    updateLayoutParams<ViewGroup.MarginLayoutParams> {
        bottomMargin = size
    }
}

fun TextView.setTextOrGone(text: CharSequence?) {
    if (text.isNullOrEmpty()) {
        gone()
        return
    }
    setText(text)
    show()
}

fun TextView.setTextOrHide(text: CharSequence?) {
    if (text.isNullOrEmpty()) {
        hide()
        return
    }
    setText(text)
    show()
}
