package com.example.mydemo.remote.mapper

import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.remote.mapper.movie.MovieRemoteMapper
import com.example.mydemo.remote.models.movies.MovieModel
import com.example.mydemo.tooltest.BaseTest
import com.example.mydemo.tooltest.autoWire
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class MovieRemoteMapperTest : BaseTest() {

    private val movieRemoteMapper : MovieRemoteMapper = autoWire()

    @Test
    fun `map to entity`() {
        // Arrange
        val movie = mockMovie()

        // Act
        val movieEntity = movieRemoteMapper.mapFromApiResponseModel(movie)

        // Assert
        assertMovieDataEquality(movieEntity, movie)
    }

    private fun mockMovie(): MovieModel {
        return MovieModel(
            id = 100,
            title = "",
            name = "",
            voteAverage = 3.8,
            posterPath = "",
            profilePath = "",
        )
    }

    private fun assertMovieDataEquality(movieEntity: MovieEntity, movie: MovieModel) {
        MatcherAssert.assertThat(movieEntity.id, CoreMatchers.`is`(movie.id))
        MatcherAssert.assertThat(movieEntity.name, CoreMatchers.`is`(movie.name))
        MatcherAssert.assertThat(movieEntity.title, CoreMatchers.`is`(movie.title))
        MatcherAssert.assertThat(movieEntity.voteAverage, CoreMatchers.`is`(movie.voteAverage))
        MatcherAssert.assertThat(movieEntity.profilePath, CoreMatchers.`is`(movie.profilePath))
        MatcherAssert.assertThat(movieEntity.posterPath, CoreMatchers.`is`(movie.posterPath))
    }

}