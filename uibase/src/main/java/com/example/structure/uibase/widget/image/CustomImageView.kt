package com.example.structure.uibase.widget.image

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Size
import androidx.appcompat.widget.AppCompatImageView
import com.example.structure.uibase.R

open class CustomImageView : AppCompatImageView {

    private var isAutoMirrored = false

    var onSizeChanged: ((Size) -> Unit)? = null

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    @SuppressLint("ResourceType")
    private fun init(attrs: AttributeSet?) {
        if (attrs != null) with(
            context.obtainStyledAttributes(attrs, R.styleable.CustomImageView)
        ) {
            isAutoMirrored =
                getBoolean(R.styleable.CustomImageView_android_autoMirrored, false)
            recycle()
        }
    }

    private fun applyAutoMirror() {
        drawable?.isAutoMirrored = isAutoMirrored
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        applyAutoMirror()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && h > 0)
            onSizeChanged?.invoke(Size(w, h))
    }
}
