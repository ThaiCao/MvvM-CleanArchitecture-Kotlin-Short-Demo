package com.example.structure.feature.home.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.structure.R
import com.example.structure.databinding.ItemHomePopularGroupBinding
import com.example.structure.presentation.model.HomeItemUi
import com.example.structure.uibase.adapter.DifferentAdapter
import com.example.structure.uibase.decoration.HorizontalPaddingDecoration
import com.example.structure.uibase.extend.addDecorationItem

class HomeMenuPopularGroupViewHolder(
    private val binding: ItemHomePopularGroupBinding,
    private val onItemClick: (HomeItemUi.PopularMovieRowItem, Int) -> Unit,
    private val onViewMorePopularMenuClick: () -> Unit
) : HomeViewHolder<HomeItemUi.PopularMovieItem>(binding.root) {
    private val viewPool = RecyclerView.RecycledViewPool()
    val popularMoviesAdapter = DifferentAdapter(
        viewHolderFactory = HomeMenuPopularGroupViewHolderFactory(
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

    override fun bind(data: HomeItemUi.PopularMovieItem, position: Int)= with(binding) {
        android.util.Log.e("TEST_DATA","HomeMenuHotGroupViewHolder data= $data")
        val childLayoutManager =
            LinearLayoutManager(rvChild.context, LinearLayoutManager.HORIZONTAL, false)
        childLayoutManager.initialPrefetchItemCount = minOf(3, data.items.size)
        rvChild.apply {
            adapter = popularMoviesAdapter
            setRecycledViewPool(viewPool)
            layoutManager = childLayoutManager
            addDecorationItem(8)
        }
        popularMoviesAdapter.submitList(data.items)
        tvMore.setOnClickListener { onViewMorePopularMenuClick() }
    }
}
