package com.example.structure.uibase.widget.floatingtopmessage

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.FrameLayout
import androidx.core.view.marginTop

class FloatTopMessageContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var mAnimator: ValueAnimator? = null
    private val viewHeight: Int
        get() {
            if (layoutParams.height > 0) return layoutParams.height
            if (height > 0) return height
            if (measuredHeight > 0) return measuredHeight
            measure(0, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            return measuredHeight
        }
    private val distance get() = viewHeight + marginTop

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        locateAt(-distance)
    }

    fun bounceSlideDownIfNeeded() {
        if (translationY == 0f) return
        bounceSlideDown()
    }

    private fun bounceSlideDown() {
        mAnimator?.cancel()
        mAnimator = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, 0f).apply {
            duration = 500
            interpolator = OvershootInterpolator()
            start()
        }
    }

    fun bounceSlideUp(onFinish: () -> Unit) {
        mAnimator?.cancel()
        mAnimator = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, -distance.toFloat()).apply {
            duration = 500
            addUpdateListener(OnFinishAnimationListener(-distance.toFloat(), onFinish))
            start()
        }
    }

    private fun locateAt(height: Int) {
        translationY = height.toFloat()
    }

    private class OnFinishAnimationListener(
        private val finishValue: Float,
        private val function: () -> Unit
    ) : ValueAnimator.AnimatorUpdateListener {
        override fun onAnimationUpdate(p0: ValueAnimator) {
            if (p0.animatedValue == finishValue) function()
        }
    }
}
