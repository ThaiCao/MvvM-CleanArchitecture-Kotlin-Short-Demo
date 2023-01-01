package com.example.structure.feature.home.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.structure.R
import com.example.structure.databinding.ItemHomeHotGroupBinding
import com.example.structure.model.presentation.HomeItemUi
import com.example.structure.uibase.adapter.DifferentAdapter
import com.example.structure.uibase.decoration.HorizontalPaddingDecoration
import com.example.structure.uibase.extend.addDecorationItem

class HomeMenuHotGroupViewHolder(
    private val binding: ItemHomeHotGroupBinding,
    private val onItemClick: (HomeItemUi.HotMovieRowItem, Int) -> Unit,
    private val onViewMoreHotMenuClick: () -> Unit
) : HomeViewHolder<HomeItemUi.HotMovieItem>(binding.root) {
    private val viewPool = RecyclerView.RecycledViewPool()
    val hotMoviesAdapter = DifferentAdapter(
        viewHolderFactory = HomeMenuHotGroupViewHolderFactory(
            onItemClick = onItemClick
        )::create
    )

    init {
        with(binding.rvChild) {
            binding.rvChild.addItemDecoration(
                HorizontalPaddingDecoration(this, R.dimen.divider_size_large)
            )
        }
    }

    override fun bind(data: HomeItemUi.HotMovieItem, position: Int)  = with(binding){
        android.util.Log.e("TEST_DATA","HomeMenuHotGroupViewHolder data= $data")
        val childLayoutManager =
            LinearLayoutManager(rvChild.context, LinearLayoutManager.HORIZONTAL, false)
        childLayoutManager.initialPrefetchItemCount = minOf(3, data.items.size)
        rvChild.apply {
            adapter = hotMoviesAdapter
            setRecycledViewPool(viewPool)
            layoutManager = childLayoutManager
            addDecorationItem(8)
        }
        hotMoviesAdapter.submitList(data.items)
        tvMore.setOnClickListener{
            android.util.Log.e("TEST_DATA","tvMore Hot Group click")
            onViewMoreHotMenuClick()
        }
    }
}
