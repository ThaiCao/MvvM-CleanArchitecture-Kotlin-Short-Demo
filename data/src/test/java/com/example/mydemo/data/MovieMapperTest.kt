package com.example.mydemo.data

import com.example.mydemo.data.base.DataTest
import com.example.mydemo.data.mapper.movie.MovieMapper
import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.tooltest.autoWire
import com.example.mydemo.tooltest.mock
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class MovieMapperTest : DataTest() {
    private val movieMapper : MovieMapper = autoWire()

    @Test
    fun mapFromEntity() {
        // Arrange
        val movieEntity = mockMovieEntity()

        // Act
        val movie = movieMapper.mapFromEntity(movieEntity)

        // Assert
        assertMovieDataEquality(movieEntity, movie)
    }

    @Test
    fun mapToEntity() {
        // Arrange
        val movie = mockMovie()

        // Act
        val movieEntity = movieMapper.mapToEntity(movie)

        // Assert
        assertMovieDataEquality(movieEntity, movie)
    }


    private fun mockMovieEntity(): MovieEntity {
        return MovieEntity(
            id = 100,
            title = "",
            name = "",
            voteAverage = 3.8,
            posterPath = "",
            profilePath = "",
        )
    }

    private fun mockMovie(): Movie {
        return Movie(
            id = 100,
            title = "",
            name = "",
            voteAverage = 3.8,
            posterPath = "",
            profilePath = "",
        )
    }

    private fun assertMovieDataEquality(movieEntity: MovieEntity, movie: Movie) {
        MatcherAssert.assertThat(movieEntity.id, CoreMatchers.`is`(movie.id))
        MatcherAssert.assertThat(movieEntity.name, CoreMatchers.`is`(movie.name))
        MatcherAssert.assertThat(movieEntity.title, CoreMatchers.`is`(movie.title))
        MatcherAssert.assertThat(movieEntity.voteAverage, CoreMatchers.`is`(movie.voteAverage))
        MatcherAssert.assertThat(movieEntity.profilePath, CoreMatchers.`is`(movie.profilePath))
        MatcherAssert.assertThat(movieEntity.posterPath, CoreMatchers.`is`(movie.posterPath))
    }

}