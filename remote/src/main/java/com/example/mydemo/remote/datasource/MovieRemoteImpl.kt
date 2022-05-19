package com.example.mydemo.remote.datasource

import com.example.mydemo.data.datasource.movie.remote.IMovieRemoteStore
import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.remote.apiservices.IMovieServiceApi
import com.example.mydemo.remote.mapper.movie.MovieRemoteMapper

class MovieRemoteImpl(
    private val moviesService: IMovieServiceApi,
    private val movieRemoteMapper: MovieRemoteMapper
): IMovieRemoteStore {
    override suspend fun getPopularsMovies(): List<MovieEntity> {
        return moviesService.getPopularsMovies().listOfMoviesResponse.map{
            movieRemoteMapper.mapFromApiResponseModel(it)
        }
    }
}