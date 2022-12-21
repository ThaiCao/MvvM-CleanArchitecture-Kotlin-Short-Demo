package com.example.structure.uibase.adapter

import androidx.recyclerview.widget.RecyclerView

@Suppress("unchecked_cast")
abstract class RecyclerAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mItems: List<T>? = null
    open val items get() = mItems ?: emptyList()

    fun submitList(items: List<T>) {
        mItems = emptyList()
        mItems = items
        doSubmit(items)
    }

    protected open fun doSubmit(items: List<T>) {
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BindAble<T>)?.bind(items[position])
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        (holder as? BindAble<T>)?.onRecycled()
    }

    override fun getItemCount(): Int = items.size

    fun clearAll() {
        mItems = emptyList()
        notifyDataSetChanged()
    }
}

interface BindAble<T> {
    fun bind(item: T)
    fun bind(item: T, payload: MutableList<Any>) {}
    fun onRecycled() {}
}

