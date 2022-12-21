package com.example.structure.feature.home.viewholder

import android.view.View
import com.example.structure.R
import com.example.structure.databinding.ItemHomeHotRowBinding
import com.example.structure.databinding.ItemHomeMenuRowBinding
import com.example.structure.databinding.ItemHomeNewRowBinding
import com.example.structure.presentation.model.HomeItemUi
import com.example.structure.presentation.model.SimpleItem
import com.example.structure.uibase.adapter.BaseViewHolder
import com.example.structure.uibase.extend.bindCenterCrop

abstract class HomeChildrenViewHolder<D : SimpleItem>(itemView: View) : BaseViewHolder<D>(itemView)
abstract class HomeViewHolder<D : HomeItemUi>(itemView: View) : BaseViewHolder<D>(itemView)

class HomeMenuRowViewHolder(
    private val binding: ItemHomeMenuRowBinding,
    private val onItemClick: (HomeItemUi.PopularMovieRowItem, position: Int) -> Unit,
) : HomeChildrenViewHolder<HomeItemUi.PopularMovieRowItem>(binding.root) {
    override fun bind(data: HomeItemUi.PopularMovieRowItem, position: Int) {
        itemView.setOnClickListener {
            onItemClick(data, position)
        }

        binding.apply {
            ivThumb.bindCenterCrop(data.imageUrl)
            tvName.text = data.name
        }
    }
}

class HomeNewRowViewHolder(
    private val binding: ItemHomeNewRowBinding,
    private val onItemClick: (HomeItemUi.NewMovieRowItem, Int) -> Unit,
) : HomeChildrenViewHolder<HomeItemUi.NewMovieRowItem>(binding.root) {
    override fun bind(data: HomeItemUi.NewMovieRowItem, position: Int) {
        itemView.setOnClickListener {
            onItemClick.invoke(data, position)
        }

        if (data.imageUrl.isEmpty()) {
            binding.ivThumb.setImageResource(R.drawable.img_placeholder)
        } else {
            binding.ivThumb.bindCenterCrop(data.imageUrl)
        }
    }
}

class HomeHotRowViewHolder(
    private val binding: ItemHomeHotRowBinding,
    private val onItemClick: (HomeItemUi.HotMovieRowItem, Int) -> Unit,
) : HomeChildrenViewHolder<HomeItemUi.HotMovieRowItem>(binding.root) {
    override fun bind(data: HomeItemUi.HotMovieRowItem, position: Int) {
        itemView.setOnClickListener {
            onItemClick.invoke(data, position)
        }

        if (data.imageUrl.isEmpty()) {
            binding.ivThumb.setImageResource(R.drawable.img_placeholder)
        } else {
            binding.ivThumb.bindCenterCrop(data.imageUrl)
        }
    }
}
