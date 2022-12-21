package com.example.structure.feature.home.viewholder

import com.example.structure.BuildConfig.IMAGE_BASE_URL
import com.example.structure.databinding.ItemHomeNewRowBinding
import com.example.structure.presentation.model.HomeItemUi
import com.example.structure.uibase.adapter.BaseViewHolder
import com.example.structure.uibase.extend.bindCenterCrop

class HomeMenuNewViewHolder(
    private val binding: ItemHomeNewRowBinding,
    private val onItemClick: (HomeItemUi.NewMovieRowItem, Int) -> Unit
) : BaseViewHolder<HomeItemUi.NewMovieRowItem>(binding.root) {
    override fun bind(data: HomeItemUi.NewMovieRowItem, position: Int) = with(binding) {
        android.util.Log.e("TEST_DATA","HomeMenuNewViewHolder data= $data --position= $position")
        tvName.text = data.name
        ivThumb.bindCenterCrop(IMAGE_BASE_URL +data.imageUrl)
        cardItemView.setOnClickListener {
            onItemClick(data, position)
        }
    }
}
