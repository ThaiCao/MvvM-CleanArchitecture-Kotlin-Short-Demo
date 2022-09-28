package com.example.structure.uicommon.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.structure.uicommon.R

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
        val headerIcon = arguments?.getInt(ARG_HEADER_ICON) ?: 0
        val title = arguments?.getString(ARG_TITLE)
        val msg = arguments?.getCharSequence(ARG_MSG, "")
        val positive = arguments?.getString(ARG_POSITIVE)
        val negative = arguments?.getString(ARG_NEGATIVE)
        val cancelable = arguments?.getBoolean(ARG_CANCELABLE) ?: false
        val enableBtnClose = arguments?.getBoolean(ARG_ENABLE_BTN_CLOSE) ?: false

        isCancelable = cancelable

        if (enableBtnClose) {
            ivClose.show()
            ivClose.setOnClickListener {
                onClickCloseListener?.invoke()
                dismiss()
            }
        }

        if (headerIcon > 0) {
            ivHeaderIcon.show()
            ivHeaderIcon.setImageResource(headerIcon)
        }

        if (isShouldAddSpaceForHeaderIcon(enableBtnClose, headerIcon)) {
            ivHeaderIcon.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = 24.toPx()
            }
        }

        if (isShouldAddSpaceForTitle(enableBtnClose, headerIcon)) {
            tvTitle.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = 24.toPx()
            }
        }
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

        if (negative.isNotNullOrBlank()) {
            btnNegative.show()
            btnNegative.text = negative
            btnNegative.setOnClickListener {
                onNegativeClickListener?.invoke()
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
