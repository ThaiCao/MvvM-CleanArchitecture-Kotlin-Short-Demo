package com.example.mydemo.presentation.stateui.movie

import com.example.mydemo.presentation.models.movie.IMovieItemUi

sealed class MovieUiState {
    data class Loading(val isLoading: Boolean): MovieUiState()
    data class Success(val moviePresentationList: List<IMovieItemUi.MoviePresentation>): MovieUiState()
    data class Error(val exception: Throwable): MovieUiState()
}
