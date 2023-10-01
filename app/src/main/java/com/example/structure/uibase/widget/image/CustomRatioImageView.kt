package com.example.structure.uibase.widget.image

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.structure.R
import com.example.structure.presentation.model.Ratio
import com.example.structure.presentation.model.Ratio.Companion.DEFAULT_RATIO

class CustomRatioImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var ratio: Float = DEFAULT_RATIO

    init {
        val attributeSet = context.obtainStyledAttributes(attrs, R.styleable.CustomRatioImageView)

        val ratioRaw = attributeSet.getString(R.styleable.CustomRatioImageView_ratio)
        ratio = toRatio(ratioRaw)

        attributeSet.recycle()
    }

    private fun toRatio(ratioRaw: String?): Float {
        ratioRaw ?: return DEFAULT_RATIO

        val array = ratioRaw.split(":")
        if (array.size != 2) return DEFAULT_RATIO
        val width = array[0].toFloatOrNull() ?: return DEFAULT_RATIO
        val height = array[1].toFloatOrNull() ?: return DEFAULT_RATIO

        return width / height
    }

    fun setRatio(ratio: Ratio) {
        if (ratio == Ratio.default())
            return
        this.ratio = ratio.width / ratio.height
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (ratio == DEFAULT_RATIO) return

        val width = measuredWidth
        val height = width / ratio
        setMeasuredDimension(width, height.toInt())
    }
}
