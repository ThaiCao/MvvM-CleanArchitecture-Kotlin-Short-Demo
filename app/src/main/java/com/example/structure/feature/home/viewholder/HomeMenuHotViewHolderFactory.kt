package com.example.structure.feature.home.viewholder

import android.view.ViewGroup
import com.example.structure.databinding.ItemHomeHotRowBinding
import com.example.structure.presentation.model.ItemHotRow
import com.example.structure.uibase.extend.get

class HomeMenuHotViewHolderFactory(private val onItemClick: (ItemHotRow, Int) -> Unit) {
    fun create(parent: ViewGroup, viewType: Int): HomeHotRowViewHolder {
        return HomeHotRowViewHolder(
            binding = parent[ItemHomeHotRowBinding::inflate],
            onItemClick = onItemClick
        )
    }
}
