package com.example.mydemo.data.mapper.movie

import com.example.mydemo.data.mapper.IEntityMapper
import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.domain.models.movies.Movie

class MovieMapper: IEntityMapper<MovieEntity, Movie> {
    override fun mapFromEntity(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            title = entity.movieTitle,
            name = entity.movieName,
            posterPath = entity.posterPath,
            profilePath = entity.profilePath,
            voteAverage = entity.voteAverage,
        )
    }

    override fun mapToEntity(domain: Movie): MovieEntity {
        return MovieEntity(
            id = domain.id,
            movieTitle = domain.title,
            movieName = domain.name,
            posterPath = domain.posterPath,
            profilePath = domain.profilePath,
            voteAverage = domain.voteAverage,
        )
    }
}