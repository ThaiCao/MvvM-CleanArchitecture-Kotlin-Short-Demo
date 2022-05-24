package com.example.mydemo.presentation.viewmodels.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mydemo.domain.models.Resource
import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.domain.usecases.base.IFlowUseCase
import com.example.mydemo.domain.usecases.features.movie.IGetMoviePopularUseCase
import com.example.mydemo.domain.usecases.features.movie.IGetMoviePopularWithCacheUseCase
import com.example.mydemo.presentation.base.BaseViewModel
import com.example.mydemo.presentation.mapper.movie.MoviePresentationMapper
import com.example.mydemo.presentation.models.movie.IMovieItemUi
import com.example.mydemo.presentation.models.movie.IMovieItemUi.MoviePresentation
import com.example.mydemo.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularMovieViewModel(
    private val getMoviePopular: IGetMoviePopularUseCase,
    private val getMoviePopularWithCache: IGetMoviePopularWithCacheUseCase,
    private val mapper: MoviePresentationMapper
) : BaseViewModel(){
    val popularMovies = SingleLiveEvent<List<MoviePresentation>>()
    val onShowMovieDetail = SingleLiveEvent<MoviePresentation>()
    val onNotFoundMovieError = SingleLiveEvent<Unit>()
    var listPopularMovieWithCache = SingleLiveEvent<List<MoviePresentation>>()

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

    fun onClickMenuItem(movieItemUi: IMovieItemUi) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            val movieItem = popularMovies.value?.find { it.id == (movieItemUi as? MoviePresentation)?.id }
            movieItem?.let{
                onShowMovieDetail.value = it
      }?.run{
                onNotFoundMovieError.call()
            }
        }
    }

    fun fetchPopularMoviesWithCache() = viewModelScope.launch(Dispatchers.Main) {
        loading.value = true
        var movieWithCacheResponseSource: LiveData<Resource<List<Movie>>>
        withContext(Dispatchers.IO) {
            movieWithCacheResponseSource = getMoviePopularWithCache(true)
        }
        try {
            listPopularMovieWithCache.addSource(movieWithCacheResponseSource) {
                android.util.Log.e("TEST_DATA","fetchPopularMoviesWithCache= ${it.data}")
                it.data?.let {
                    listPopularMovieWithCache.value = it.map{mapper.mapToPresentation(it)}
                }
                loading.value = false
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
