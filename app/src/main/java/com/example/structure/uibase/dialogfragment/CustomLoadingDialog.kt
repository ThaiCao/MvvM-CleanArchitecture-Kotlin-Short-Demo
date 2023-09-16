package com.example.structure.uibase.dialogfragment

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatImageView
import com.example.structure.R
import com.example.structure.uibase.extend.loadGif


class CustomLoadingDialog(context: Context) {
    private val dialog: Dialog?

    val isShowing: Boolean
        get() = dialog != null && dialog.isShowing

    init {

        dialog = Dialog(context, R.style.LoadingTheme)
        val view: View = LayoutInflater.from(context).inflate(R.layout.dialog_loading_view, null)

        val ivLoading: AppCompatImageView? = view.findViewById(R.id.ivLoading)
        ivLoading?.loadGif(R.drawable.loading_gift)

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
        dialog.setContentView(view)
    }

    fun showDialog() {
        if (dialog?.isShowing == false) dialog.show()
    }

    fun hideDialog() {
        if (dialog?.isShowing == true) dialog.dismiss()
    }
}
