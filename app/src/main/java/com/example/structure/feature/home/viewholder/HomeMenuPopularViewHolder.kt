package com.example.structure.feature.home.viewholder

import com.example.structure.BuildConfig.IMAGE_BASE_URL
import com.example.structure.databinding.ItemHomeMenuRowBinding
import com.example.structure.presentation.model.HomeItemUi
import com.example.structure.uibase.adapter.BaseViewHolder
import com.example.structure.uibase.extend.bindCenterCrop

class HomeMenuPopularViewHolder(
    private val binding: ItemHomeMenuRowBinding,
    private val onItemClick: (HomeItemUi.PopularMovieRowItem, Int) -> Unit
) : BaseViewHolder<HomeItemUi.PopularMovieRowItem>(binding.root) {
    override fun bind(data: HomeItemUi.PopularMovieRowItem, position: Int) = with(binding) {
        android.util.Log.e("TEST_DATA","HomeMenuPopularViewHolder data= $data --position= $position")
        tvName.text = data.name
        ivThumb.bindCenterCrop(IMAGE_BASE_URL+ data.imageUrl)
        cardItemView.setOnClickListener {
            onItemClick(data, position)
        }
    }
}
