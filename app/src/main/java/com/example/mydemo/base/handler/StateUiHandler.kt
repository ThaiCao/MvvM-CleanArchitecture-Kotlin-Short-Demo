package com.example.mydemo.base.handler

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.mydemo.R
import com.example.mydemo.common.utils.safe
import com.example.mydemo.features.dialog.CustomLoadingDialog
import com.example.mydemo.presentation.base.BaseViewModel
import com.example.mydemo.presentation.models.ErrorUi
import com.example.mydemo.utils.common.showSimpleDialog


interface StateUiOwner {
    val stateUiHandler: StateUiHandler get() = DefaultStateUiHandler()
    val viewModels: StateUiHandler.ViewModels get() = StateUiHandler.ViewModels()
}

interface StateUiHandler {
    fun observe(fragment: Fragment, owner: BaseViewModel)

    class ViewModels(vararg viewModels: BaseViewModel) {
        val items = viewModels
    }
}

open class DefaultStateUiHandler : StateUiHandler {

    private var loadingDialog: CustomLoadingDialog? = null

    override fun observe(fragment: Fragment, owner: BaseViewModel) = with(fragment) {

        owner.error.observe(viewLifecycleOwner) {
            showGenericErrorMessage(fragment = fragment, errorUI = it)
        }

        owner.loading.observe(viewLifecycleOwner) {
            showDefaultDialogLoading(requireContext(), it)
        }
    }

    private fun showDefaultDialogLoading(context: Context, isShowLoading: Boolean) {
        if (isShowLoading && !loadingDialog?.isShowing.safe(false)) {
            loadingDialog = CustomLoadingDialog(context = context).also { it.showDialog() }
        } else {
            loadingDialog?.hideDialog()
        }
    }

    private fun showGenericErrorMessage(fragment: Fragment, errorUI: ErrorUi) {
        fragment.run {
            val title = (errorUI as? ErrorUi.Message)?.title ?: getString(R.string.oops)
            val msg = (errorUI as? ErrorUi.Message)?.message
                ?: getString(R.string.error_general_error_message)
            showSimpleDialog(
                title = title,
                msg = msg,
                textBtnPositive = getString(R.string.close),
            )
        }
    }
}
