package com.example.structure.uibase.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ViewHolderFactory {
    fun get(parent: ViewGroup, item: Any): RecyclerView.ViewHolder
    fun setup(view: RecyclerView) {}
}
