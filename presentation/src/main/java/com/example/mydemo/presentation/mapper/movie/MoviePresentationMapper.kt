package com.example.mydemo.presentation.mapper.movie

import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.presentation.mapper.IPresentationMapper
import com.example.mydemo.presentation.models.movie.IMovieItemUi.MoviePresentation

class MoviePresentationMapper : IPresentationMapper<MoviePresentation, Movie> {
    override fun mapToPresentation(domain: Movie): MoviePresentation {
        return MoviePresentation(
            id = domain.id,
            title = domain.title,
            name = domain.name,
            posterPath = domain.posterPath,
            profilePath = domain.profilePath,
            voteAverage = domain.voteAverage,
        )
    }

}