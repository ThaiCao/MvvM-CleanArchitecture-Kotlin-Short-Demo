package com.example.mydemo.features.popularmovie.viewholder

import android.view.View
import android.view.ViewGroup
import com.example.mydemo.base.adapter.BaseViewHolder
import com.example.mydemo.common.utils.toPosterPath
import com.example.mydemo.databinding.ItemPopularMovieBinding
import com.example.mydemo.presentation.models.movie.IMovieItemUi
import com.example.mydemo.presentation.models.movie.IMovieItemUi.Companion.TYPE_POPULAR_MOVIE
import com.example.mydemo.utils.common.bindImage
import com.example.mydemo.utils.common.get

class MovieItemViewHolderFactory(private val onClickItemListener: ((IMovieItemUi) -> Unit)? = null) {
    @Suppress("UNCHECKED_CAST")
    fun create(parent: ViewGroup, viewType: Int): MovieViewHolder<IMovieItemUi> {
        return when (viewType) {
            TYPE_POPULAR_MOVIE -> PopularMovieViewHolder(
                binding = parent[ItemPopularMovieBinding::inflate]
            )
            else -> error("Does not support type $viewType")
        } as MovieViewHolder<IMovieItemUi>
    }

    abstract class MovieViewHolder<D : IMovieItemUi>(itemView: View) :
        BaseViewHolder<D>(itemView)

    inner class PopularMovieViewHolder(
        private val binding: ItemPopularMovieBinding
    ) : MovieViewHolder<IMovieItemUi.MoviePresentation>(binding.root) {

        init {
            binding.root.setOnClickListener { view ->
                (view.tag as? IMovieItemUi.MoviePresentation)?.let {
                    onClickItemListener?.invoke(it)
                }
            }
        }

        override fun bind(data: IMovieItemUi.MoviePresentation, position: Int) {
            with(binding) {
                root.tag = data
                tvMovieName.text = data.title
                tvVotes.text = data.voteAverage.toString()
                ivImageView.bindImage(data.posterPath.toPosterPath())
            }
        }
    }
}
