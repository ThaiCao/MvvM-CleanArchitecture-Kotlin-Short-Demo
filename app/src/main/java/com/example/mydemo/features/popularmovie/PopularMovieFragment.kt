package com.example.mydemo.features.popularmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mydemo.R
import com.example.mydemo.base.fragment.BaseFragment
import com.example.mydemo.databinding.FragmentPopularMovieBinding
import com.example.mydemo.presentation.viewmodels.movie.PopularMovieViewModel
import com.example.mydemo.utils.common.viewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class PopularMovieFragment : BaseFragment(R.layout.fragment_popular_movie) {

    private val menuNavigator: PopularMovieNavigator by inject()

    private val viewModel: PopularMovieViewModel by viewModel()

    val binding by viewBinding(FragmentPopularMovieBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_to_movie_detail)
        }*/
        viewModel.fetchPopularMovies()
    }

}