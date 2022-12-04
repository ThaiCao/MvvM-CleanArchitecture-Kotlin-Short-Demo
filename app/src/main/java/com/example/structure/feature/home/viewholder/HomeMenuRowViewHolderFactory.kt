package com.example.structure.feature.home.viewholder

import android.view.ViewGroup
import com.example.structure.databinding.ItemHomeMenuRowBinding
import com.example.structure.presentation.model.ItemHomeMenuRow
import com.example.structure.uibase.extend.get

class HomeMenuRowViewHolderFactory(private val onItemClick: (ItemHomeMenuRow, Int) -> Unit) {
    fun create(parent: ViewGroup, viewType: Int): HomeMenuRowViewHolder {
        return HomeMenuRowViewHolder(
            binding = parent[ItemHomeMenuRowBinding::inflate],
            onItemClick = onItemClick
        )
    }
}
