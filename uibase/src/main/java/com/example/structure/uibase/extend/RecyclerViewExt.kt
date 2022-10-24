package com.example.structure.uibase.extend

import androidx.recyclerview.widget.RecyclerView
import com.example.structure.uibase.decoration.ItemOffsetDecoration

fun RecyclerView.addDecorationItem(offset: Int) {
    if (this.itemDecorationCount == 0)
        addItemDecoration(ItemOffsetDecoration(offset.toPx()))
}
