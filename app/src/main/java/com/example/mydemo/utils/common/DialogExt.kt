package com.example.mydemo.utils.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.mydemo.R
import com.example.mydemo.features.dialog.DialogFragmentLifecycleObserver
import com.example.mydemo.features.dialog.InfoDialog


fun <D : DialogFragment> AppCompatActivity.showDialogFragment(tag: String, dialog: () -> D): D? {

    return DialogFragmentLifecycleObserver<D>(
        lifecycleOwner = this,
        fm = supportFragmentManager,
        tag = tag
    ).show {
        dialog.invoke()
    }
}

fun <D : DialogFragment> Fragment?.showDialogFragment(tag: String, dialog: () -> D): D? {
    val viewLifecycleOwner = this?.viewLifecycleOwner
    val childFragmentManager = this?.childFragmentManager

    if (viewLifecycleOwner == null || childFragmentManager == null) return null

    return DialogFragmentLifecycleObserver<D>(
        lifecycleOwner = viewLifecycleOwner,
        fm = childFragmentManager,
        tag = tag
    ).show {
        dialog.invoke()
    }
}

fun Fragment?.dismissDialogFragmentByTag(tag: String) {
    (this?.childFragmentManager?.findFragmentByTag(tag) as? DialogFragment)?.dismiss()
}

fun AppCompatActivity.showSimpleDialog(
    headerIcon: Int? = null,
    title: String,
    msg: String,
    textBtnPositive: String? = null,
    textBtnNegative: String? = null,
    cancelable: Boolean = false,
    enableBtnClose: Boolean = true,
    onClickCloseListener: (() -> Unit)? = null,
    onPositiveClickListener: (() -> Unit)? = null,
    onNegativeClickListener: (() -> Unit)? = null
) {
    showDialogFragment(InfoDialog.TAG) {
        InfoDialog.build(
            headerIcon = headerIcon,
            title = title,
            msg = msg,
            textBtnPositive = textBtnPositive,
            textBtnNegative = textBtnNegative,
            cancelable = cancelable,
            enableBtnClose = enableBtnClose,
            onClickCloseListener = onClickCloseListener,
            onPositiveClickListener = onPositiveClickListener,
            onNegativeClickListener = onNegativeClickListener,
        )
    }
}

fun Fragment.showSimpleDialog(
    headerIcon: Int? = null,
    title: String,
    msg: CharSequence,
    textBtnPositive: String? = null,
    textBtnNegative: String? = null,
    cancelable: Boolean = false,
    enableBtnClose: Boolean = true,
    onClickCloseListener: (() -> Unit)? = null,
    onPositiveClickListener: (() -> Unit)? = null,
    onNegativeClickListener: (() -> Unit)? = null
) {
    showDialogFragment(InfoDialog.TAG) {
        InfoDialog.build(
            headerIcon = headerIcon,
            title = title,
            msg = msg,
            textBtnPositive = textBtnPositive,
            textBtnNegative = textBtnNegative,
            cancelable = cancelable,
            enableBtnClose = enableBtnClose,
            onClickCloseListener = onClickCloseListener,
            onPositiveClickListener = onPositiveClickListener,
            onNegativeClickListener = onNegativeClickListener,
        )
    }
}

fun Fragment.showError(msg: String) {
    showSimpleDialog(
        title = getString(R.string.oops),
        msg = msg,
        textBtnPositive = getString(R.string.close),
    )
}
