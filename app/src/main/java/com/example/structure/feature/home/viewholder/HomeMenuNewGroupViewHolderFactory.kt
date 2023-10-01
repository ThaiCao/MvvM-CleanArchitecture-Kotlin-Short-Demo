package com.example.structure.feature.home.viewholder

import android.view.ViewGroup
import com.example.structure.databinding.ItemHomeNewRowBinding
import com.example.structure.presentation.model.HomeItemUi
import com.example.structure.uibase.extend.get

class HomeMenuNewGroupViewHolderFactory(private val onItemClick: (HomeItemUi.NewMovieRowItem, Int) -> Unit) {
    fun create(parent: ViewGroup, viewType: Int): HomeMenuNewViewHolder {
        return HomeMenuNewViewHolder(
            binding = parent[ItemHomeNewRowBinding::inflate],
            onItemClick = onItemClick
        )
    }
}
