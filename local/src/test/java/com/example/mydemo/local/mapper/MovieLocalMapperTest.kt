package com.example.mydemo.local.mapper

import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.local.mapper.movie.MovieLocalMapper
import com.example.mydemo.local.models.movies.MovieLocal
import com.example.mydemo.tooltest.BaseTest
import com.example.mydemo.tooltest.autoWire
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class MovieLocalMapperTest : BaseTest(){

    private val movieMapper : MovieLocalMapper = autoWire()

    @Test
    fun `map from entity`() {
        // Arrange
        val movieEntity = mockMovieEntity()

        // Act
        val movie = movieMapper.mapToLocal(movieEntity)

        // Assert
        assertMovieDataEquality(movieEntity, movie)
    }

    @Test
    fun `map to entity`() {
        // Arrange
        val movie = mockMovieLocal()

        // Act
        val movieEntity = movieMapper.mapFromLocal(movie)

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

    private fun mockMovieLocal(): MovieLocal {
        return MovieLocal(
            movieId  = 100,
            title = "",
            name = "",
            voteAverage = 3.8,
            posterPath = "",
            profilePath = "",
        )
    }

    private fun assertMovieDataEquality(movieEntity: MovieEntity, movie: MovieLocal) {
        MatcherAssert.assertThat(movieEntity.id, CoreMatchers.`is`(movie.movieId))
        MatcherAssert.assertThat(movieEntity.name, CoreMatchers.`is`(movie.name))
        MatcherAssert.assertThat(movieEntity.title, CoreMatchers.`is`(movie.title))
        MatcherAssert.assertThat(movieEntity.voteAverage, CoreMatchers.`is`(movie.voteAverage))
        MatcherAssert.assertThat(movieEntity.profilePath, CoreMatchers.`is`(movie.profilePath))
        MatcherAssert.assertThat(movieEntity.posterPath, CoreMatchers.`is`(movie.posterPath))
    }
}