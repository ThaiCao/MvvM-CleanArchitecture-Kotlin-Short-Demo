package com.example.structure.model.presentation

interface ItemDifferent {
    val type: Int

    fun areItemsTheSame(new: ItemDifferent) = false

    fun areContentsTheSame(new: ItemDifferent) = false
}

open class SimpleItem : ItemDifferent {
    override val type: Int get() = Int.MAX_VALUE
}

