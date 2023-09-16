package com.example.structure.presentation.model

interface ItemDifferent {
    val type: Int

    fun areItemsTheSame(new: ItemDifferent) = false

    fun areContentsTheSame(new: ItemDifferent) = false
}

open class SimpleItem : ItemDifferent {
    override val type: Int get() = Int.MAX_VALUE
}

