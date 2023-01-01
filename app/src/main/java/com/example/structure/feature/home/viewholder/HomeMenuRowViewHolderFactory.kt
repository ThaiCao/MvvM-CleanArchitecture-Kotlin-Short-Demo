package com.example.structure.feature.home.viewholder

import android.view.ViewGroup
import com.example.structure.databinding.ItemHomeMenuRowBinding
import com.example.structure.model.presentation.HomeItemUi
import com.example.structure.uibase.extend.get

class HomeMenuRowViewHolderFactory(private val onItemClick: (HomeItemUi.PopularMovieRowItem, Int) -> Unit) {
    fun create(parent: ViewGroup, viewType: Int): HomeMenuRowViewHolder {
        return HomeMenuRowViewHolder(
            binding = parent[ItemHomeMenuRowBinding::inflate],
            onItemClick = onItemClick
        )
    }
}
