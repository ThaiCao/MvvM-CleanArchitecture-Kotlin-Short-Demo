package com.example.structure.uicommon.extend

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addDecorationItem(offset: Int) {
    if (this.itemDecorationCount == 0)
        addItemDecoration(ItemOffsetDecoration(offset.toPx()))
}
