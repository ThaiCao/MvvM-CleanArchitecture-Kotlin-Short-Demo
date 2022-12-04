package com.example.structure.uibase.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder2<D, V : ViewBinding>(val binding: V) :
    RecyclerView.ViewHolder(binding.root), BindAble<D> {

    val context: Context = binding.root.context

    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }
}
