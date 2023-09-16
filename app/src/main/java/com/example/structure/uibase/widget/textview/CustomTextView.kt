package com.example.structure.uibase.widget.textview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet

open class CustomTextView : androidx.appcompat.widget.AppCompatTextView {

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init(attrs)
    }

    @SuppressLint("ResourceType")
    private fun init(attrs: AttributeSet?) {
    }
}
