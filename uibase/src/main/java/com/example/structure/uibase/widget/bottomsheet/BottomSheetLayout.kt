package com.example.structure.uibase.widget.bottomsheet

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ListView
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

class BottomSheetLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        const val STATE_NONE = -1
        const val STATE_COLLAPSED = 0
        const val STATE_EXPANDED = 1
        const val STATE_SLIDING = 2
        const val STATE_SLIDING_DOWN = 3
        const val STATE_SLIDING_UP = 4
    }

    private var mIDLECallback: () -> Unit = {}
    private var mIgnoreInterceptCallbacks = arrayListOf<(MotionEvent) -> Boolean>()
    private lateinit var mGroup: ViewGroup
    private lateinit var mExpandedView: View
    private lateinit var mCollapsedView: View
    private var mState = STATE_NONE

    private val mTouchPoint = TouchPoint()
    private var mDragHelper = ViewDragHelper.create(this, 1.0f, OurViewDragHelperCallbacks())

    private val offsetTop get() = (mGroup.layoutParams as MarginLayoutParams).topMargin
    private val offsetBottom get() = measuredHeight - mCollapsedView.measuredHeight
    private val isCollapsed: Boolean get() = mCollapsedView.isShown
    val collapsedHeight: Int get() = mCollapsedView.measuredHeight

    var onStateChangedListener: (Int) -> Unit = {}
    var onSlidingListener: (Float) -> Unit = {}

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount != 1) error("Bottom sheet content should has only one child")
        mGroup = getChildAt(0) as ViewGroup
        if (mGroup.childCount != 2) error("Bottom sheet child content should have 2 child")
        mCollapsedView = mGroup.getChildAt(0)
        mExpandedView = mGroup.getChildAt(1)

        mGroup.isClickable = true
        mGroup.isFocusable = true

        setCollapsed(true)

        mCollapsedView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            idle {
                if (isOutOfScreen(mGroup.bottom) && isCollapsed) {
                    mDragHelper.smoothSlideViewTo(mGroup, 0, offsetBottom)
                }
            }
        }
    }

    private fun idle(function: () -> Unit) {
        if (mDragHelper.viewDragState == ViewDragHelper.STATE_IDLE) {
            function()
        } else {
            mIDLECallback = function
        }
    }

    private fun isOutOfScreen(offset: Int): Boolean {
        return offset > this.bottom
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (mIgnoreInterceptCallbacks.any { it(ev) }) return false
        return mDragHelper!!.shouldInterceptTouchEvent(ev)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val offset = when {
            isInEditMode -> offsetBottom
            mDragHelper.viewDragState == ViewDragHelper.STATE_DRAGGING
                || mDragHelper.viewDragState == ViewDragHelper.STATE_SETTLING -> mSavedGroupTop
            isCollapsed -> offsetBottom
            else -> offsetTop
        }

        mGroup.layout(mGroup.left, offset, mGroup.right, offset + mGroup.measuredHeight)
        notifySlideChanged(offset)
    }

    private fun onSlideChanged(percent: Float) {
        val alpha = min((255 * (1 - percent)).toInt(), 150)
        setBackgroundColor(Color.argb(alpha, 0, 0, 0))

        mCollapsedView.alpha = getCollapsedAlpha(percent)
        mExpandedView.alpha = getExpandedAlpha(percent)

        onSlidingListener(percent)

        when (percent) {
            0f -> {
                setCollapsed(false)
                notifyStateChanged(STATE_EXPANDED)
            }
            1f -> {
                setCollapsed(true)
                notifyStateChanged(STATE_COLLAPSED)
            }
            else -> {
                mCollapsedView.visibility = View.VISIBLE
                mExpandedView.visibility = View.VISIBLE
                notifyStateChanged(STATE_SLIDING)
            }
        }
    }

    private fun notifyStateChanged(state: Int) {
        if (mState == state) return
        mState = state
        onStateChangedListener(state)
    }

    private fun getExpandedAlpha(percent: Float): Float {
        val anchor = 0.5f
        if (percent > anchor) return 0f
        return (anchor - percent) / 0.5f
    }

    private fun getCollapsedAlpha(percent: Float): Float {
        val anchor = 0.5f
        if (percent < anchor) return 0f
        return (percent - anchor) / anchor
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mTouchPoint.set(event)
                if (mTouchPoint.isOutOf(mGroup)) return false
            }
        }

        mDragHelper?.processTouchEvent(event)

        return true
    }

    private var mSavedGroupTop: Int = 0

    override fun computeScroll() {
        super.computeScroll()
        if (mDragHelper?.continueSettling(true) == true) {
            ViewCompat.postInvalidateOnAnimation(this)
        } else {
            mSavedGroupTop = mGroup.top
        }
    }

    fun collapse() {
        notifyStateChanged(STATE_SLIDING_UP)
        mDragHelper?.smoothSlideViewTo(mGroup, 0, offsetBottom)
        invalidate()
    }

    fun expand() {
        notifyStateChanged(STATE_SLIDING_DOWN)
        mDragHelper?.smoothSlideViewTo(mGroup, 0, offsetTop)
        invalidate()
    }

    fun isExpand(): Boolean {
        return mState == STATE_EXPANDED
    }

    fun isSliding() = when (mState) {
        STATE_SLIDING, STATE_SLIDING_UP, STATE_SLIDING_DOWN -> true
        else -> false
    }

    fun addNestedScrollable(view: View) {
        val nestedScrollable = NestedScrollable(this)
        mIgnoreInterceptCallbacks.add {
            view.isShown && nestedScrollable.run { !view.isAtTop && view.isTouchedAt(it) }
        }
        view.setOnTouchListener(nestedScrollable)
    }

    fun notifySlideChanged(top: Int) {
        val ratio = (top - offsetTop.toFloat()) / (offsetBottom - offsetTop)
        onSlideChanged(min(ratio, 1f))
    }

    class TouchPoint {

        private var mDownY: Float = 0f
        private var mDownX: Float = 0f

        fun isOutOf(view: View): Boolean {
            return mDownY < view.top
        }

        fun isClicked(event: MotionEvent): Boolean {
            val deltaX = event.rawX - mDownX
            val deltaY = event.rawY - mDownY
            return sqrt(deltaX * deltaX + deltaY * deltaY) < 5
        }

        fun set(event: MotionEvent) {
            mDownX = event.rawX
            mDownY = event.rawY
        }
    }

    private inner class OurViewDragHelperCallbacks : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int
        ) {
            notifySlideChanged(top)
        }

        override fun onViewDragStateChanged(state: Int) {
            if (state == ViewDragHelper.STATE_IDLE) mIDLECallback()
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return max(min(top, offsetBottom), offsetTop)
        }

        override fun getViewVerticalDragRange(child: View): Int {
            return offsetBottom - offsetTop
        }

        override fun onViewReleased(
            releasedChild: View,
            xvel: Float,
            yvel: Float
        ) {
            if (yvel > 0) {
                mDragHelper?.settleCapturedViewAt(releasedChild.left, offsetBottom)
            } else {
                mDragHelper?.settleCapturedViewAt(releasedChild.left, offsetTop)
            }
            invalidate()
        }
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        val myState = SavedState(superState)
        myState.isCollapsed = isCollapsed
        return myState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as? SavedState
        super.onRestoreInstanceState(savedState?.superState)
        savedState ?: return

        setCollapsed(savedState.isCollapsed)
    }

    private fun setCollapsed(collapsed: Boolean) {
        mCollapsedView.visibility = if (collapsed) View.VISIBLE else View.INVISIBLE
        mExpandedView.visibility = if (collapsed) View.INVISIBLE else View.VISIBLE
    }

    private class SavedState : BaseSavedState {
        var isCollapsed: Boolean = true

        constructor(superState: Parcelable?) : super(superState)

        constructor(parcel: Parcel) : super(parcel) {
            isCollapsed = parcel.readInt() == 1
        }

        override fun writeToParcel(out: Parcel?, flags: Int) {
            super.writeToParcel(out, flags)
            val value = out?.readInt() ?: 1
            isCollapsed = value == 1
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState?> =
                object : Parcelable.Creator<SavedState?> {
                    override fun createFromParcel(`in`: Parcel): SavedState {
                        return SavedState(`in`)
                    }

                    override fun newArray(size: Int): Array<SavedState?> {
                        return arrayOfNulls(size)
                    }
                }
        }
    }
}

class NestedScrollable(
    private val parentScrollView: ViewGroup
) : View.OnTouchListener {
    private var mDownY: Float = 0f

    private val ListView.isAtTop: Boolean
        get() {
            if (childCount == 0) return true
            return firstVisiblePosition == 0
        }

    private val RecyclerView.isAtTop: Boolean
        get() {
            if (childCount == 0) return true
            return (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() == 0
        }

    fun View.isTouchedAt(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val location = IntArray(2)
            getLocationOnScreen(location)
            val left = location[0]
            val top = location[1]
            val right = left + measuredWidth
            val bottom = top + measuredHeight
            return ev.rawY >= top && ev.rawY <= bottom && ev.rawX >= left && ev.rawX <= right
        }
        return false
    }

    val View.isAtTop: Boolean
        get() {
            if (this is ListView) return isAtTop
            if (this is RecyclerView) return isAtTop
            error("Not support ${this.javaClass.name}")
        }

    private fun swipeDown(ev: MotionEvent): Boolean {
        return ev.rawY > mDownY
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, ev: MotionEvent): Boolean {

        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownY = ev.rawY
                if (!v.isAtTop)
                    parentScrollView.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                if (swipeDown(ev) && !v.isAtTop) {
                    parentScrollView.requestDisallowInterceptTouchEvent(true)
                }
            }
        }
        return false
    }
}
