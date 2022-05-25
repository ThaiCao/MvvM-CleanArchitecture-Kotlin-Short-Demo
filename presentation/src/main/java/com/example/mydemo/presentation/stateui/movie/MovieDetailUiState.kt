package com.example.mydemo.presentation.stateui.movie

import com.example.mydemo.presentation.models.movie.IMovieItemUi

sealed class MovieDetailUiState {
    data class Success(val moviePresentation: IMovieItemUi.MoviePresentation): MovieDetailUiState()
    data class Error(val exception: String): MovieDetailUiState()
    data class InitUiState(val isInit: Boolean): MovieDetailUiState()
}
