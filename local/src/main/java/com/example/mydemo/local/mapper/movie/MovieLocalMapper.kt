package com.example.mydemo.local.mapper.movie

import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.local.mapper.ILocalMapper
import com.example.mydemo.local.models.movies.MovieLocal

class MovieLocalMapper : ILocalMapper<MovieLocal, MovieEntity>{
    override fun mapFromLocal(local: MovieLocal): MovieEntity {
        return MovieEntity(
            id = local.movieId,
            movieTitle = local.title,
            movieName = local.name,
            posterPath = local.posterPath,
            profilePath = local.profilePath,
            voteAverage = local.voteAverage,
        )
    }

    override fun mapToLocal(entity: MovieEntity): MovieLocal {
        return MovieLocal(
            movieId = entity.id,
            name = entity.movieName,
            title = entity.movieTitle,
            posterPath = entity.posterPath,
            profilePath = entity.profilePath,
            voteAverage = entity.voteAverage
        )
    }
}