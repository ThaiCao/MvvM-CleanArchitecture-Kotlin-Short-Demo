package com.example.mydemo.features.moviedetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.mydemo.R
import com.example.mydemo.base.fragment.BaseFragment
import com.example.mydemo.common.FragmentParams
import com.example.mydemo.common.utils.toPosterPath
import com.example.mydemo.databinding.FragmentMovieDetailBinding
import com.example.mydemo.presentation.models.movie.IMovieItemUi
import com.example.mydemo.presentation.viewmodels.MovieDetailViewModel
import com.example.mydemo.utils.common.bindImage
import com.example.mydemo.utils.common.viewBinding
import kotlinx.parcelize.Parcelize
import org.koin.androidx.viewmodel.ext.android.viewModel


@Parcelize
data class MovieDetailFragmentParam(
    var movie: IMovieItemUi.MoviePresentation? = null,
) : FragmentParams

class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail) {

    val binding by viewBinding(FragmentMovieDetailBinding::bind)
    var params: MovieDetailFragmentParam? = null
    private val viewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        params = FragmentParams[arguments]

        val movie = params?.movie?: run{ return}
        viewModel.onGetCurrentMovie(movie)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        bindingView()
        observer()
    }

    private fun bindingView() = with(binding) {

    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observer() = with(viewModel) {
        movieSelected.observe(viewLifecycleOwner) {
            onDisplayMovieDetail(it)
        }
    }

    private fun onDisplayMovieDetail(movie: IMovieItemUi.MoviePresentation){
        binding.tvMovieName.text = movie.title
        binding.tvVotes.text = movie.voteAverage.toString()
        binding.ivImageView.bindImage(movie.posterPath.toPosterPath())
    }

}