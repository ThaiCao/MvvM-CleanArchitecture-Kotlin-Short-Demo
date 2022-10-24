package com.example.structure.uibase.bottomsheet

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatDialog
import com.example.structure.uibase.R
import com.google.android.material.bottomsheet.BottomSheetBehavior

open class CustomBottomSheetDialog(context: Context) :
    AppCompatDialog(context, R.style.BottomSheetDialogTheme) {

    private lateinit var mContent: View
    private lateinit var mBehavior: BottomSheetBehavior<FrameLayout>

    private var designBottomSheet:FrameLayout? = null
    private var rootView:View? = null
    private var touchOutside:View? = null

    private var mOnShowListener: DialogInterface.OnShowListener? = null
    private val mLayoutCallback = BottomSheetLayoutCallback()
    var onBackPressed: (() -> Boolean) = { true }

    init {
        createContainerView()
    }

    private fun createContainerView() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        rootView = LayoutInflater.from(context).inflate(R.layout.custom_bottom_sheet_dialog, null)
        rootView?.let { rootView ->
            super.setContentView(rootView)

            designBottomSheet = rootView.findViewById(R.id.design_bottom_sheet)
            designBottomSheet?.let {
                mBehavior = BottomSheetBehavior.from(it)
                mBehavior.peekHeight = 0
                mBehavior.addBottomSheetCallback(mLayoutCallback)
            }
            touchOutside = rootView.findViewById(R.id.touch_outside)
            touchOutside?.setOnClickListener {
                close()
            }
            super.setOnShowListener {
                mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                mOnShowListener?.onShow(it)
            }
        }

    }

    inner class BottomSheetLayoutCallback :
        BottomSheetBehavior.BottomSheetCallback(),
        View.OnLayoutChangeListener {
        private var mState: Int = BottomSheetBehavior.STATE_COLLAPSED
        private var mLayoutChanged: Boolean = false
        private var mSlideOffset = -1f

        override fun onStateChanged(p0: View, p1: Int) {
            if (p1 == BottomSheetBehavior.STATE_COLLAPSED) {
                close()
            }
            if (p1 == BottomSheetBehavior.STATE_EXPANDED) {
                if (mSlideOffset < 1f && mLayoutChanged) {
                    mContent.requestLayout()
                }
                mLayoutChanged = false
            }

            mState = p1
        }

        override fun onSlide(p0: View, p1: Float) {
            mSlideOffset = p1
        }

        override fun onLayoutChange(
            v: View?,
            left: Int,
            top: Int,
            right: Int,
            bottom: Int,
            oldLeft: Int,
            oldTop: Int,
            oldRight: Int,
            oldBottom: Int
        ) {
            if (mState != BottomSheetBehavior.STATE_EXPANDED) {
                mLayoutChanged = true
            }
        }
    }

    override fun setOnShowListener(listener: DialogInterface.OnShowListener?) {
        mOnShowListener = listener
    }

    override fun setContentView(layoutResID: Int) {
        rootView?.let { setContentView(it) }
    }

    override fun setContentView(view: View) {
        setContentView(view, null)
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams?) {
        designBottomSheet?.removeAllViews()
        if (params == null) designBottomSheet?.addView(view)
        else designBottomSheet?.addView(view, params)
        mContent = view
        view.removeOnLayoutChangeListener(mLayoutCallback)
        view.addOnLayoutChangeListener(mLayoutCallback)
    }

    private fun close() {
        cancel()
    }

    override fun dismiss() {
        super.dismiss()
        mBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onBackPressed() {
        if (onBackPressed.invoke()) {
            super.onBackPressed()
        }
    }
}
