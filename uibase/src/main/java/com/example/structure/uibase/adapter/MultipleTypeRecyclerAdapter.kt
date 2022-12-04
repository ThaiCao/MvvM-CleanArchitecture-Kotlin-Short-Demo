package com.example.structure.uibase.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

@Suppress("unchecked_cast")
class MultipleTypeRecyclerAdapter<D>(private val viewHolderFactory: ViewHolderFactory) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mItems: ArrayList<D> = arrayListOf()
    val items get() = mItems

    fun submit(items: List<D>) {
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        viewHolderFactory.setup(recyclerView)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return viewHolderFactory.get(parent, items[viewType]!!)
    }

    @Suppress("unchecked_cast")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mItems[position]
        (holder as? BindAble<D>)?.bind(item)
    }

    @Suppress("unchecked_cast")
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)

        (holder as? BindAble<Any>)?.onRecycled()
    }

    @Suppress("unchecked_cast")
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            val bindAble = holder as? BindAble<D>
            if (bindAble != null) return bindAble.bind(mItems[position], payloads)
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun itemByPosition(position: Int) = mItems[position]
}

