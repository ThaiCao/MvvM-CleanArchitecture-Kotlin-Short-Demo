package com.example.structure.feature.home

import com.example.structure.R
import com.example.structure.feature.detail.MovieDetailFragmentParam
import com.example.structure.navigation.BaseNavigator
import com.example.structure.navigation.BaseNavigatorImpl
import com.example.structure.uibase.extend.navOptions
import com.example.structure.uibase.extend.safeNavigate

interface HomeNavigator : BaseNavigator {
    fun openCategory()

    fun openMovieList()

    fun openMovieDetail(movieId: String? = null)

}

class HomeNavigatorImpl : BaseNavigatorImpl(),  HomeNavigator {
    override fun openCategory() {
        requireNavigator().safeNavigate(
            destinationId = R.id.global_action_to_category,
            navOptions = { navOptions(withAnim = true) }
        )
    }

    override fun openMovieList() {
        requireNavigator().safeNavigate(
            destinationId = R.id.global_action_to_movie_list,
            navOptions = { navOptions(withAnim = true) }
        )
    }

    override fun openMovieDetail(movieId: String?) {
        requireNavigator().safeNavigate(
            destinationId = R.id.global_action_to_movie_detail,
            navOptions = { navOptions(withAnim = true) },
            bundle = {
                MovieDetailFragmentParam(
                    movieId = movieId,
                ).toBundle()
            }
        )
    }

}
