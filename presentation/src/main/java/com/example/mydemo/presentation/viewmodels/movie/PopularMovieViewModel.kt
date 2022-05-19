package com.example.mydemo.presentation.viewmodels.movie

import com.example.mydemo.domain.usecases.base.IFlowUseCase
import com.example.mydemo.domain.usecases.features.movie.IGetMoviePopularUseCase
import com.example.mydemo.presentation.base.BaseViewModel
import com.example.mydemo.presentation.mapper.movie.MoviePresentationMapper
import com.example.mydemo.presentation.models.MoviePresentation
import com.example.mydemo.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PopularMovieViewModel(
    private val getMoviePopular: IGetMoviePopularUseCase,
    private val mapper: MoviePresentationMapper
) : BaseViewModel(){
    val popularMovies = SingleLiveEvent<List<MoviePresentation>>()

    fun fetchPopularMovies(){
        viewModelScope.launch {
            loading.value = true
            getMoviePopular(IFlowUseCase.None())
                .flowOn(Dispatchers.IO)
                .catch{
                    it.printStackTrace()
                    loading.value = false
                }.collect {
                    popularMovies.value = it.map{mapper.mapToPresentation(it)}
                    loading.value = false
                }
        }
    }
}