package com.example.structure.feature.home.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.structure.R
import com.example.structure.databinding.ItemHomeNewGroupBinding
import com.example.structure.model.presentation.HomeItemUi
import com.example.structure.uibase.adapter.DifferentAdapter
import com.example.structure.uibase.decoration.HorizontalPaddingDecoration
import com.example.structure.uibase.extend.addDecorationItem

class HomeMenuNewGroupViewHolder(
    private val binding: ItemHomeNewGroupBinding,
    private val onItemClick: (HomeItemUi.NewMovieRowItem, Int) -> Unit,
    private val onViewMoreNewMenuClick: () -> Unit
) : HomeViewHolder<HomeItemUi.NewMovieItem>(binding.root) {
    private val viewPool = RecyclerView.RecycledViewPool()
    val newMoviesAdapter = DifferentAdapter(
        viewHolderFactory = HomeMenuNewGroupViewHolderFactory(
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

    override fun bind(data: HomeItemUi.NewMovieItem, position: Int)= with(binding) {
        android.util.Log.e("TEST_DATA","HomeMenuHotGroupViewHolder data= $data")
        val childLayoutManager =
            LinearLayoutManager(rvChild.context, LinearLayoutManager.HORIZONTAL, false)
        childLayoutManager.initialPrefetchItemCount = minOf(3, data.items.size)
        rvChild.apply {
            adapter = newMoviesAdapter
            setRecycledViewPool(viewPool)
            layoutManager = childLayoutManager
            addDecorationItem(8)
        }
        newMoviesAdapter.submitList(data.items)
        tvMore.setOnClickListener { onViewMoreNewMenuClick() }
    }
}
