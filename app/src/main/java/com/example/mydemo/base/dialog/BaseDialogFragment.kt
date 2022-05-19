package com.example.mydemo.base.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.example.mydemo.utils.common.getScreenWidth

abstract class BaseDialogFragment(@LayoutRes private val contentLayoutId: Int = 0) :
    DialogFragment(contentLayoutId) {

    override fun onResume() {
        super.onResume()
        dialog?.window?.apply {
            val width = getScreenWidth(0.9f)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
            setGravity(Gravity.CENTER)
        }
    }
}
