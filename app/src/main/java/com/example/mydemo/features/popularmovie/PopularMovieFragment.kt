package com.example.mydemo.features.popularmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mydemo.R
import com.example.mydemo.base.adapter.DifferentAdapter
import com.example.mydemo.base.decoration.SimpleSpaceDecoration
import com.example.mydemo.base.fragment.BaseFragment
import com.example.mydemo.databinding.FragmentPopularMovieBinding
import com.example.mydemo.features.popularmovie.viewholder.MovieItemViewHolderFactory
import com.example.mydemo.presentation.viewmodels.movie.PopularMovieViewModel
import com.example.mydemo.utils.common.showError
import com.example.mydemo.utils.common.showLoadingDialog
import com.example.mydemo.utils.common.toPx
import com.example.mydemo.utils.common.viewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class PopularMovieFragment : BaseFragment(R.layout.fragment_popular_movie) {

    private val popularMovieNavigator: PopularMovieNavigator by inject()

    private val viewModel: PopularMovieViewModel by viewModel()

    val binding by viewBinding(FragmentPopularMovieBinding::bind)

    private val moviePopularAdapter = DifferentAdapter(
        viewHolderFactory = MovieItemViewHolderFactory(
            onClickItemListener = {
                viewModel.onClickMenuItem(it)
            },
        )::create
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        observer()
        viewModel.fetchPopularMovies()
    }

    private fun bindView() = with(binding) {
        rvMovie.adapter = moviePopularAdapter
        rvMovie.addItemDecoration(SimpleSpaceDecoration(12.toPx()))
    }

    private fun observer() = with(viewModel) {
         loading.observe(viewLifecycleOwner) {
             showLoadingDialog(requireContext(), it)
         }

         popularMovies.observe(viewLifecycleOwner) {
             moviePopularAdapter.submitList(it)

         }
        onShowMovieDetail.observe(viewLifecycleOwner) {
            popularMovieNavigator.openPopularMovie(it)
        }
        onNotFoundMovieError.observe(viewLifecycleOwner) {
            showError(getString(R.string.error_movie_not_found))
        }
    }
}