package com.example.mydemo.presentation.viewmodels

import com.example.mydemo.presentation.base.BaseViewModel
import com.example.mydemo.presentation.models.movie.IMovieItemUi
import com.example.mydemo.presentation.utils.SingleLiveEvent

class MovieDetailViewModel : BaseViewModel(){
    val movieSelected = SingleLiveEvent<IMovieItemUi.MoviePresentation>()

    fun onGetCurrentMovie(movie: IMovieItemUi.MoviePresentation){
        movieSelected.value = movie
    }
}