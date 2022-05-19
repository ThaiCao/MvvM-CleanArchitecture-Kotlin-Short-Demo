package com.example.mydemo.features.dialog

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.example.mydemo.R
import com.example.mydemo.base.dialog.BaseDialogFragment
import com.example.mydemo.common.utils.isNotNullOrBlank
import com.example.mydemo.common.utils.safe
import com.example.mydemo.databinding.DialogInfoBinding
import com.example.mydemo.utils.common.show
import com.example.mydemo.utils.common.viewBinding

class InfoDialog : BaseDialogFragment(R.layout.dialog_info) {
    private val binding by viewBinding(DialogInfoBinding::bind)

    companion object {
        const val TAG = "InfoDialog"

        private const val ARG_HEADER_ICON = "arg_header_icon"
        private const val ARG_TITLE = "arg_title"
        private const val ARG_MSG = "arg_msg"
        private const val ARG_POSITIVE = "arg_positive"
        private const val ARG_NEGATIVE = "arg_negative"
        private const val ARG_CANCELABLE = "arg_cancelable"
        private const val ARG_ENABLE_BTN_CLOSE = "ARG_ENABLE_BTN_CLOSE"

        fun build(
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
        ): InfoDialog {
            return InfoDialog().apply {
                arguments = bundleOf(
                    ARG_HEADER_ICON to headerIcon,
                    ARG_TITLE to title,
                    ARG_CANCELABLE to cancelable,
                    ARG_ENABLE_BTN_CLOSE to enableBtnClose,

                    ARG_POSITIVE to textBtnPositive,
                    ARG_NEGATIVE to textBtnNegative,
                )
                arguments?.putCharSequence(ARG_MSG, msg)

                this.onClickCloseListener = onClickCloseListener
                this.onPositiveClickListener = onPositiveClickListener
                this.onNegativeClickListener = onNegativeClickListener
            }
        }
    }

    private var onClickCloseListener: (() -> Unit)? = null
    private var onPositiveClickListener: (() -> Unit)? = null
    private var onNegativeClickListener: (() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
    }

    private fun bindView() = with(binding) {
        val title = arguments?.getString(ARG_TITLE)
        val msg = arguments?.getCharSequence(ARG_MSG, "")
        val positive = arguments?.getString(ARG_POSITIVE)
        val cancelable = arguments?.getBoolean(ARG_CANCELABLE) ?: false

        isCancelable = cancelable

        tvTitle.text = title.safe()
        tvMsg.text = msg

        if (positive.isNotNullOrBlank()) {
            btnPositive.show()
            btnPositive.text = positive
            btnPositive.setOnClickListener {
                onPositiveClickListener?.invoke()
                dismiss()
            }
        }
    }

    private fun isShouldAddSpaceForTitle(enableBtnClose: Boolean, headerIcon: Int): Boolean {
        return !enableBtnClose || headerIcon > 0
    }

    private fun isShouldAddSpaceForHeaderIcon(enableBtnClose: Boolean, headerIcon: Int): Boolean {
        return !enableBtnClose && headerIcon > 0
    }
}
