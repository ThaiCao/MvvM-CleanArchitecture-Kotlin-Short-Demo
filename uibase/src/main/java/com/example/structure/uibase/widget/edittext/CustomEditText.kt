package com.example.structure.uibase.widget.edittext

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

open class CustomEditText : AppCompatEditText {

    private var textWatchers: MutableList<TextWatcher>? = null

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
    }

    override fun addTextChangedListener(watcher: TextWatcher) {
        if (textWatchers == null) textWatchers = mutableListOf()
        textWatchers?.add(watcher)

        super.addTextChangedListener(watcher)
    }

    override fun removeTextChangedListener(watcher: TextWatcher) {
        textWatchers?.remove(watcher)

        super.removeTextChangedListener(watcher)
    }

    fun setTextSilent(text: String) {
        removeAllTextChangedListeners()
        setText(text)
        addAllTextChangedListeners()
    }

    private fun removeAllTextChangedListeners() {
        textWatchers?.forEach {
            super.removeTextChangedListener(it)
        }
    }

    private fun addAllTextChangedListeners() {
        textWatchers?.forEach {
            super.addTextChangedListener(it)
        }
    }

    fun setMaxLength(maxLength: Int) {
        filters = arrayOf<InputFilter>(LengthFilter(maxLength))
    }
}
