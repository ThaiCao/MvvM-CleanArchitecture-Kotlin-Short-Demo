package com.example.structure.feature.detail

import android.os.Bundle
import android.view.View
import com.example.structure.R
import com.example.structure.common.fragmentparams.FragmentParams
import com.example.structure.databinding.FragmentMovieDetailBinding
import com.example.structure.navigation.DirectionNavigateBack
import com.example.structure.presentation.feature.moviedetail.MovieDetailViewModel
import com.example.structure.uibase.extend.viewBinding
import com.example.structure.uibase.fragment.BaseFragment
import com.example.structure.uibase.handler.StateUiOwner
import kotlinx.parcelize.Parcelize
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@Parcelize
data class MovieDetailFragmentParam(
    val movieId: String? = null,
    val directionNavigateBack: DirectionNavigateBack = DirectionNavigateBack.NONE,
) : FragmentParams

class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail), StateUiOwner {
    private val binding by viewBinding(FragmentMovieDetailBinding::bind)
    private val viewModel: MovieDetailViewModel by viewModel()
    private val movieDetailNavigator: MovieDetailNavigator by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        android.util.Log.e("TEST_DATA","MovieDetailFragment onViewCreated")
        bindView()
        observer()
       // viewModel.getMovieDetailById(id)
    }

    private fun bindView() = with(binding) {
       btnLogin.setOnClickListener {
           movieDetailNavigator.openLogin()
       }
        btnPlayer.setOnClickListener {
            movieDetailNavigator.openPlayer()
        }
    }

    private fun observer() = with(viewModel) {
//        MovieDetailDAtaa.observe(viewLifecycleOwner) {
//
//        }

    }
}
