package com.example.structure.feature.home.viewholder

import android.view.View
import com.example.structure.R
import com.example.structure.databinding.ItemHomeHotRowBinding
import com.example.structure.databinding.ItemHomeMenuRowBinding
import com.example.structure.databinding.ItemHomeNewRowBinding
import com.example.structure.presentation.model.ItemHome
import com.example.structure.presentation.model.ItemHomeMenuRow
import com.example.structure.presentation.model.ItemHotRow
import com.example.structure.presentation.model.ItemNewRow
import com.example.structure.uibase.adapter.BaseViewHolder
import com.example.structure.uibase.extend.bindCenterCrop

abstract class HomeViewHolder<D : ItemHome>(itemView: View) : BaseViewHolder<D>(itemView)

class HomeMenuRowViewHolder(
    private val binding: ItemHomeMenuRowBinding,
    private val onItemClick: (ItemHomeMenuRow, position: Int) -> Unit,
) : HomeViewHolder<ItemHomeMenuRow>(binding.root) {
    override fun bind(data: ItemHomeMenuRow, position: Int) {
        itemView.setOnClickListener {
            onItemClick(data, position)
        }

        binding.apply {
            ivThumb.bindCenterCrop(data.img)
            tvName.text = data.name
        }
    }
}

class HomeNewRowViewHolder(
    private val binding: ItemHomeNewRowBinding,
    private val onItemClick: (ItemNewRow, Int) -> Unit,
) : HomeViewHolder<ItemNewRow>(binding.root) {
    override fun bind(data: ItemNewRow, position: Int) {
        itemView.setOnClickListener {
            onItemClick.invoke(data, position)
        }

        if (data.img.isEmpty()) {
            binding.ivThumb.setImageResource(R.drawable.img_placeholder)
        } else {
            binding.ivThumb.bindCenterCrop(data.img)
        }
    }
}

class HomeHotRowViewHolder(
    private val binding: ItemHomeHotRowBinding,
    private val onItemClick: (ItemHotRow, Int) -> Unit,
) : HomeViewHolder<ItemHotRow>(binding.root) {
    override fun bind(data: ItemHotRow, position: Int) {
        itemView.setOnClickListener {
            onItemClick.invoke(data, position)
        }

        if (data.img.isEmpty()) {
            binding.ivThumb.setImageResource(R.drawable.img_placeholder)
        } else {
            binding.ivThumb.bindCenterCrop(data.img)
        }
    }
}
