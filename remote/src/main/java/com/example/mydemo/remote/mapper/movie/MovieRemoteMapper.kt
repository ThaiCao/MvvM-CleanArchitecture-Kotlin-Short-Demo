package com.example.mydemo.remote.mapper.movie

import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.remote.mapper.IRemoteMapper
import com.example.mydemo.remote.models.movies.MovieModel

class MovieRemoteMapper : IRemoteMapper<MovieModel, MovieEntity> {

    override fun mapFromApiResponseModel(model: MovieModel): MovieEntity {
        return MovieEntity(
            id = model.id,
            title = model.title,
            name = model.name,
            posterPath = model.posterPath,
            profilePath = model.profilePath,
            voteAverage = model.voteAverage,
        )
    }

}