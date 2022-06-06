package com.example.mydemo.features.popularmovie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mydemo.R
import com.example.mydemo.base.adapter.DifferentAdapter
import com.example.mydemo.base.decoration.SimpleSpaceDecoration
import com.example.mydemo.base.fragment.BaseFragment
import com.example.mydemo.databinding.FragmentPopularMovieBinding
import com.example.mydemo.features.popularmovie.viewholder.MovieItemViewHolderFactory
import com.example.mydemo.presentation.stateui.movie.MovieDetailUiState
import com.example.mydemo.presentation.stateui.movie.MovieUiState
import com.example.mydemo.presentation.viewmodels.movie.PopularMovieViewModel
import com.example.mydemo.utils.common.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularMovieFragment : BaseFragment(R.layout.fragment_popular_movie) {

    private val popularMovieNavigator: PopularMovieNavigator by inject()

    private val viewModel: PopularMovieViewModel by viewModel()

    val binding by viewBinding(FragmentPopularMovieBinding::bind)

    private val moviePopularAdapter = DifferentAdapter(
        viewHolderFactory = MovieItemViewHolderFactory(
            onClickItemListener = {
//                viewModel.onClickMenuItem(it)
                viewModel.onClickMenuItemUiState(it)
            },
        )::create
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        observer()
//        viewModel.fetchPopularMovies()
        viewModel.fetchPopularMoviesUiState()
//        viewModel.fetchPopularMoviesWithCache()
    }

    private fun bindView() = with(binding) {
        rvMovie.adapter = moviePopularAdapter
        rvMovie.addItemDecoration(SimpleSpaceDecoration(12.toPx()))
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun observer() = with(viewModel) {
        // normal case
        loading.observe(viewLifecycleOwner) {
            showLoadingDialog(requireContext(), it)
        }
        popularMovies.observe(viewLifecycleOwner) {
            moviePopularAdapter.submitList(it)
        }

        onNotFoundMovieError.observe(viewLifecycleOwner) {
            showError(getString(R.string.error_movie_not_found))
        }


        listPopularMovieWithCache.observe(viewLifecycleOwner) {
            moviePopularAdapter.submitList(it)
        }

        onShowMovieDetail.observe(viewLifecycleOwner) {
            popularMovieNavigator.openPopularMovie(it)
        }

        // using state flow
        // Start a coroutine in the lifecycle scope
        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieDetailUiState.collect{uiState ->
                    when(uiState) {
                        is MovieDetailUiState.InitUiState -> { }
                        is MovieDetailUiState.Success  -> popularMovieNavigator.openPopularMovie(uiState.moviePresentation)
                        is MovieDetailUiState.Error  -> showError(uiState.exception)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieUiState.collect { uiState ->
                    // New value received
                    when (uiState) {
                        is MovieUiState.Success -> {
                            moviePopularAdapter.submitList(uiState.moviePresentationList)
                        }
                        is MovieUiState.Error -> showError(uiState.exception.toString())
                        is MovieUiState.LoadingShow -> showLoadingDialog(requireContext())
                        is MovieUiState.LoadingHide -> hideLoadingDialog()
                    }
                }
            }
        }

    }
}
