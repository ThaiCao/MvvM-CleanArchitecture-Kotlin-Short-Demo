package com.example.mydemo.utils.common

import android.content.Context
import com.example.mydemo.common.utils.safe
import com.example.mydemo.features.dialog.CustomLoadingDialog

var loadingDialog: CustomLoadingDialog? = null
fun showLoadingDialog(context: Context, isShowLoading: Boolean) {
    loadingDialog = if (isShowLoading && !loadingDialog?.isShowing.safe(false)) {
        CustomLoadingDialog(context = context).also { it.showDialog() }
    } else {
        loadingDialog?.hideDialog()
        null
    }
}

fun showLoadingDialog(context: Context) {
    loadingDialog = if (!loadingDialog?.isShowing.safe(false)) {
        CustomLoadingDialog(context = context).also { it.showDialog() }
    } else {
        loadingDialog?.hideDialog()
        null
    }
}

fun hideLoadingDialog() {
    loadingDialog?.hideDialog()
    loadingDialog = null
}
