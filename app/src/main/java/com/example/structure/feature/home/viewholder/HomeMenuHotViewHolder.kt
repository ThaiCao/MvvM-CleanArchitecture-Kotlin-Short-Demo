package com.example.structure.feature.home.viewholder

import com.example.structure.BuildConfig.IMAGE_BASE_URL
import com.example.structure.databinding.ItemHomeHotRowBinding
import com.example.structure.model.presentation.HomeItemUi
import com.example.structure.uibase.adapter.BaseViewHolder
import com.example.structure.uibase.extend.bindCenterCrop

class HomeMenuHotViewHolder(
    private val binding: ItemHomeHotRowBinding,
    private val onItemClick: (HomeItemUi.HotMovieRowItem, Int) -> Unit
) : BaseViewHolder<HomeItemUi.HotMovieRowItem>(binding.root) {
    override fun bind(data: HomeItemUi.HotMovieRowItem, position: Int) = with(binding) {
        android.util.Log.e("TEST_DATA","HomeMenuHotViewHolder data= $data --position= $position")
        tvName.text = data.name
        ivThumb.bindCenterCrop(IMAGE_BASE_URL + data.imageUrl)
        cardItemView.setOnClickListener {
            onItemClick(data, position)
        }
    }
}
