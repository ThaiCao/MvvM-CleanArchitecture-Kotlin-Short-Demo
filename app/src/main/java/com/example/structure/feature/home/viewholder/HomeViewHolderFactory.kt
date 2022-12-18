package com.example.structure.feature.home.viewholder

import android.view.View
import android.view.ViewGroup
import com.example.structure.databinding.ItemHomeHotGroupBinding
import com.example.structure.databinding.ItemHomeSpaceBinding
import com.example.structure.presentation.model.HomeItemUi
import com.example.structure.presentation.model.HomeItemUi.Companion.TYPE_MOVIE_HOT
import com.example.structure.presentation.model.HomeItemUi.Companion.TYPE_SPACE
import com.example.structure.uibase.adapter.BaseViewHolder
import com.example.structure.uibase.extend.get
import com.example.structure.uibase.extend.getThemeColor
import com.example.structure.uibase.extend.toPx

class HomeViewHolderFactory(
    private val onItemHotMenuClick: (item: HomeItemUi.HotMovieRowItem, position: Int) -> Unit
) {

    fun create(parent: ViewGroup, viewType: Int): HomeViewHolder<HomeItemUi> {
        return when (viewType) {

            TYPE_SPACE -> HomeSpaceViewHolder(
                binding = parent[ItemHomeSpaceBinding::inflate]
            )
            TYPE_MOVIE_HOT -> HomeMenuHotGroupViewHolder(
                binding = parent[ItemHomeHotGroupBinding::inflate],
                onItemClick = onItemHotMenuClick
            )
            else -> error("Does not support type $viewType")
        }  as HomeViewHolder<HomeItemUi>
    }

    inner class HomeSpaceViewHolder(binding: ItemHomeSpaceBinding) :
        HomeViewHolder<HomeItemUi.SpaceItem>(binding.root) {
        override fun bind(data: HomeItemUi.SpaceItem, position: Int) {
            itemView.layoutParams.apply {
                height = data.height.toPx()
            }

            itemView.setBackgroundColor(getThemeColor(context = getContext(), data.colorAttr))
        }
    }

//    abstract class HomeViewHolder<D : HomeItemUi>(itemView: View) : BaseViewHolder<D>(itemView)
}
