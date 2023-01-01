package com.example.structure.feature.home.viewholder

import android.view.ViewGroup
import com.example.structure.databinding.ItemHomeHotRowBinding
import com.example.structure.model.presentation.HomeItemUi
import com.example.structure.uibase.extend.get

class HomeMenuHotGroupViewHolderFactory(private val onItemClick: (HomeItemUi.HotMovieRowItem, Int) -> Unit) {
    fun create(parent: ViewGroup, viewType: Int): HomeMenuHotViewHolder {
        return HomeMenuHotViewHolder(
            binding = parent[ItemHomeHotRowBinding::inflate],
            onItemClick = onItemClick
        )
    }
}
