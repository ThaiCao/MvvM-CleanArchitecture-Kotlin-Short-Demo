package com.example.structure.feature.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.example.structure.R
import com.example.structure.common.extend.safe
import com.example.structure.databinding.DialogInternetConnectionFailBinding
import com.example.structure.network.NetworkConnection
import com.example.structure.uibase.dialogfragment.BaseFullScreenDialogFragment
import com.example.structure.uibase.dialogfragment.CustomLoadingDialog
import com.example.structure.uibase.extend.viewBinding
import org.koin.android.ext.android.inject

class InternetConnectionFailBottomSheet :
    BaseFullScreenDialogFragment(R.layout.dialog_internet_connection_fail) {

    private val networkConnection: NetworkConnection by inject()

    private val binding by viewBinding(DialogInternetConnectionFailBinding::bind)

    var onDismissListener: (() -> Unit)? = null
    var loadingDialog: CustomLoadingDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() = with(binding) {

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        showLoadingDialog(requireContext(), false)
        onDismissListener?.invoke()
    }

    private fun showLoadingDialog(context: Context, isShowLoading: Boolean) {
        loadingDialog = if (isShowLoading && !loadingDialog?.isShowing.safe(false)) {
            CustomLoadingDialog(context = context).also { it.showDialog() }
        } else {
            loadingDialog?.hideDialog()
            null
        }
    }

    override fun onDestroyView() {
        loadingDialog?.hideDialog()
        super.onDestroyView()
    }

    companion object {

        const val TAG: String = "InternetConnectionFailBottomSheet"
    }
}
