package com.example.structure.uibase.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.structure.presentation.model.SingleSelectableList

@Suppress("unchecked_cast")
abstract class SingleSelectableRecyclerAdapter<T> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mItems: SingleSelectableList<T>? = null
    open val items get() = mItems ?: SingleSelectableList()

    fun submitList(items: SingleSelectableList<T>) {
        mItems = items
        doSubmit(items)
    }

    protected open fun doSubmit(items: SingleSelectableList<T>) {
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BindAble<T>)?.bind(items[position])
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        (holder as? BindAble<T>)?.onRecycled()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = items.size
}

