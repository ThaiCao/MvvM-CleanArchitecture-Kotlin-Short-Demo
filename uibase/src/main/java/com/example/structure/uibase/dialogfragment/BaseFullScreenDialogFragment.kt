package com.example.structure.uibase.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.structure.uibase.R
import com.example.structure.uibase.handler.StateUiOwner

open class BaseFullScreenDialogFragment(@LayoutRes private val contentLayoutId: Int = 0) :
    BaseDialogFragment(contentLayoutId) {

    open fun getAnimationStyle() = R.style.DialogLeftToRightAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (contentLayoutId != 0) {
            inflater.inflate(contentLayoutId, container, false)
        } else null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addStateInterceptIfNeeded()
    }

    private fun addStateInterceptIfNeeded() {
        if (this is StateUiOwner) {
            viewModels.items.forEach {
                stateUiHandler.observe(this, it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.apply {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            setLayout(width, height)
            setWindowAnimations(getAnimationStyle())
            setDimAmount(0F)
        }
    }
}

