package com.example.structure.uibase.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.structure.uibase.extend.isLayoutDirectionRtl

open class ItemOffsetDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager is GridLayoutManager) {
            val layoutManager = parent.layoutManager as GridLayoutManager
            val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams

            // item position
            val position = layoutParams.absoluteAdapterPosition
            val spanCount = layoutManager.spanCount

            // check span size if any
            var realPosition = 0
            for (i in 0 until position) {
                val spanSize = layoutManager.spanSizeLookup.getSpanSize(i)
                realPosition += spanSize
            }
            val column = realPosition % spanCount // item column
            val row = realPosition / spanCount
            var left = 0
            var right = 0
            if (isLayoutDirectionRtl()) {
                right = if (column == 0) 0 else space
            } else {
                left = if (column == 0) 0 else space
            }

            val top = if (row == 0) 0 else space
            outRect.set(left, top, right, 0)
        } else if (parent.layoutManager is LinearLayoutManager) {
            // item position
            val orientation = (parent.layoutManager as LinearLayoutManager).orientation
            val position = parent.getChildAdapterPosition(view)
            if (orientation == LinearLayoutManager.VERTICAL) {
                val top = if (position == 0) 0 else space
                outRect.set(0, top, 0, 0)
            } else {
                val left = if (position == 0) 0 else space
                outRect.set(left, 0, 0, 0)
            }
        }
    }
}
