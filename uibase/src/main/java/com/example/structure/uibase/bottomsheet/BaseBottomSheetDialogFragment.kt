package com.example.structure.uibase.bottomsheet

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.structure.uibase.R
import com.example.structure.uibase.handler.StateUiOwner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment(@LayoutRes private val layoutId: Int) :
    BottomSheetDialogFragment() {

    open var onBackPressed: (() -> Boolean) = { true }
    open var onCancelListener: (() -> Unit) = { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme)
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return CustomBottomSheetDialog(requireContext()).apply {
            setOnCancelListener {
                this@BaseBottomSheetDialogFragment.dismiss()
            }

            setOnDismissListener {
                this@BaseBottomSheetDialogFragment.dismiss()
            }
            onBackPressed = { this@BaseBottomSheetDialogFragment.onBackPressed.invoke() }
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        this@BaseBottomSheetDialogFragment.onCancelListener.invoke()
        super.onCancel(dialog)
    }
}
