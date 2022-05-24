package com.example.mydemo.remote.datasource

import com.example.mydemo.data.datasource.movie.remote.IMovieRemoteStore
import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.remote.apiservices.IMovieServiceApi
import com.example.mydemo.remote.mapper.movie.MovieRemoteMapper
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MovieRemoteImpl(
    private val moviesService: IMovieServiceApi,
    private val movieRemoteMapper: MovieRemoteMapper
): IMovieRemoteStore {
    override suspend fun getPopularsMovies(): List<MovieEntity> {
        return moviesService.getPopularsMovies().listOfMoviesResponse.map{
            movieRemoteMapper.mapFromApiResponseModel(it)
        }
    }

    override suspend fun getPopularsMoviesAsync(): List<MovieEntity> {
//        return GlobalScope.async {moviesService.getPopularsMoviesAsync().await().listOfMoviesResponse.map{
//            movieRemoteMapper.mapFromApiResponseModel(it)}
//        }
        return moviesService.getPopularsMoviesAsync().listOfMoviesResponse.map{
            movieRemoteMapper.mapFromApiResponseModel(it)}
    }
}
