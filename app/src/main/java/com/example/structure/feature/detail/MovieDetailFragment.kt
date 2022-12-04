package com.example.structure.feature.detail

import com.example.structure.R
import com.example.structure.common.fragmentparams.FragmentParams
import com.example.structure.navigation.DirectionNavigateBack
import com.example.structure.uibase.fragment.BaseFragment
import com.example.structure.uibase.handler.StateUiOwner
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailFragmentParam(
    val movieId: String? = null,
    val directionNavigateBack: DirectionNavigateBack = DirectionNavigateBack.NONE,
) : FragmentParams

class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail), StateUiOwner {
}
