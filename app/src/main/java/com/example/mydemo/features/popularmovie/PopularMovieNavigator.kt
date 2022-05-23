package com.example.mydemo.features.popularmovie

import com.example.mydemo.presentation.models.movie.IMovieItemUi.MoviePresentation
import com.example.mydemo.R
import com.example.mydemo.utils.common.navOptionsBottomToTop
import com.example.mydemo.utils.common.safeNavigate
import com.example.mydemo.base.navigator.BaseNavigator
import com.example.mydemo.base.navigator.BaseNavigatorImpl
import com.example.mydemo.features.moviedetail.MovieDetailFragmentParam

interface PopularMovieNavigator : BaseNavigator {
    fun openPopularMovie(movie: MoviePresentation)
}

class PopularMovieNavigatorImpl : BaseNavigatorImpl(), PopularMovieNavigator {
    override fun openPopularMovie(movie: MoviePresentation) {
        requireNavigator().safeNavigate(
            destinationId = R.id.action_to_movie_detail,
            bundle = {
                MovieDetailFragmentParam(
                    movie = movie
                ).toBundle()
            },
            navOptions = { navOptionsBottomToTop(withAnim = true) }
        )
    }
}
