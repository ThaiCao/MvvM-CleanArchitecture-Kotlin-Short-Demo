package com.example.structure.uibase.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceVerticalDecoration(var padding: Int = 0) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val manager = parent.layoutManager ?: return
        when (manager.getPosition(view)) {
            0 -> outRect.top = padding
            manager.itemCount - 1 -> outRect.bottom = padding
        }
    }
}
