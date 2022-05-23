package com.example.mydemo.features.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.mydemo.R
import com.example.mydemo.databinding.DialogLoadingViewBinding

class CustomLoadingDialog(context: Context) {
    private val dialog: Dialog?

    val isShowing: Boolean
        get() = dialog != null && dialog.isShowing

    init {

        dialog = Dialog(context, R.style.LoadingTheme)
        val binding = DialogLoadingViewBinding.inflate(LayoutInflater.from(context))

        dialog.window?.apply {
            setGravity(Gravity.CENTER)
            setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
            )
            setFlags(
                WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                WindowManager.LayoutParams.FLAG_DIM_BEHIND
            )
            attributes.dimAmount = 0.5f
        }

        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)
    }

    fun showDialog() {
        dialog?.show()
    }

    fun hideDialog() {
        dialog?.dismiss()
    }
}
