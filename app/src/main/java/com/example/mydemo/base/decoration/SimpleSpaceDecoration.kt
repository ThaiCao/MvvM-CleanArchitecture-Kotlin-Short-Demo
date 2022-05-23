package com.example.mydemo.base.decoration

import android.graphics.Rect
import android.view.View
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView

class SimpleSpaceDecoration(
    private val space: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)

        val leftSpace = space - parent.paddingStart - parent.marginStart
        val rightSpace = space - parent.paddingEnd - parent.marginEnd

        var topSpace = space - parent.paddingTop - parent.marginTop
        var bottomSpace = space - parent.paddingBottom - parent.marginBottom

        if (position > 0) {
            topSpace /= 2
        }
        bottomSpace /= 2

        outRect.set(leftSpace, topSpace, rightSpace, bottomSpace)
    }
}
