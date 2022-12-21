package com.example.structure.uibase.decoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import com.example.structure.uibase.extend.isLayoutRtl

class HorizontalPaddingDecoration(
    view: View,
    @DimenRes paddingStartRes: Int = 0,
    @DimenRes paddingEndRes: Int = paddingStartRes,
    @DimenRes spacingRes: Int = paddingStartRes
) : RecyclerView.ItemDecoration() {
    private val paddingStart = getDimension(view, paddingStartRes)
    private val paddingEnd = getDimension(view, paddingEndRes)
    private val spacing = getDimension(view, spacingRes)

    private fun getDimension(context: View, paddingStartRes: Int): Int {
        return if (paddingStartRes == 0) 0
        else context.resources.getDimensionPixelSize(paddingStartRes)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: return
        if (itemCount <= 1) return

        when (parent.getChildAdapterPosition(view)) {
            0 -> if (parent.isLayoutRtl) outRect.right = paddingEnd else outRect.left =
                paddingStart
            itemCount - 1 -> {
                if (parent.isLayoutRtl) {
                    outRect.right = spacing
                    outRect.left = paddingStart
                } else {
                    outRect.left = spacing
                    outRect.right = paddingEnd
                }
            }
            else -> if (parent.isLayoutRtl) outRect.right = spacing else outRect.left = spacing
        }
    }
}
