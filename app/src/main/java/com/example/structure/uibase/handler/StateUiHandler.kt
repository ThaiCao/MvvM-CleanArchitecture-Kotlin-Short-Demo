package com.example.structure.uibase.handler

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.structure.common.extend.isNotNullOrBlank
import com.example.structure.common.extend.safe
import com.example.structure.presentation.base.BaseViewModel
import com.example.structure.R
import com.example.structure.presentation.model.ErrorUi
import com.example.structure.presentation.model.NotificationUi
import com.example.structure.uibase.dialogfragment.CustomLoadingDialog
import com.example.structure.uibase.extend.showSimpleDialog
import com.example.structure.uibase.widget.floatingtopmessage.FloatingTopMessageView

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

open class DefaultStateUiHandler : StateUiHandler, DefaultLifecycleObserver {

    private var loadingDialog: CustomLoadingDialog? = null
    private var topFloatingErrorMessage: FloatingTopMessageView? = null

    override fun observe(fragment: Fragment, owner: BaseViewModel) = with(fragment) {
        topFloatingErrorMessage = FloatingTopMessageView.newInstance(fragment)

        viewLifecycleOwner.lifecycle.addObserver(this@DefaultStateUiHandler)

        owner.error.observe(viewLifecycleOwner) {
            showGenericErrorMessage(fragment = fragment, errorUI = it)
        }

        owner.loading.observe(viewLifecycleOwner) {
            showDefaultDialogLoading(requireContext(), it)
        }

        owner.topFloatingError.observe(viewLifecycleOwner) { notifyUi ->
            val message = notifyUi.takeIf { it.message.isNotNullOrBlank() } ?: NotificationUi.Info(getString(R.string.error_general_error_message))
            topFloatingErrorMessage?.setMessage(message)?.show()
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
                textBtnPositive = getString(R.string.ok),
            )
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        loadingDialog?.hideDialog()
        super.onDestroy(owner)
    }
}
