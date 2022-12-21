package com.example.structure.uibase.decoration

import android.graphics.Rect
import android.view.View
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView

class VerticalPaddingDecoration(
    private val space: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)
        if (position == 0) return
        val bottomSpace = space - parent.paddingBottom - parent.marginBottom
        outRect.set(0, 0, 0, bottomSpace)
    }
}
