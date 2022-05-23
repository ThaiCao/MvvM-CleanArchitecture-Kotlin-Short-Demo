package com.example.mydemo.presentation.mapper
import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.presentation.mapper.movie.MoviePresentationMapper
import com.example.mydemo.presentation.models.movie.IMovieItemUi
import com.example.mydemo.tooltest.BaseTest
import com.example.mydemo.tooltest.autoWire
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class MoviePresentationMapperTest : BaseTest(){
    private val movieMapper : MoviePresentationMapper = autoWire()

    @Test
    fun `map to presentation`() {
        // Arrange
        val movie = mockMovie()

        // Act
        val moviePresentation = movieMapper.mapToPresentation(movie)

        // Assert
        assertMovieDataEquality(moviePresentation, movie)
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

    private fun assertMovieDataEquality(moviePresentation: IMovieItemUi.MoviePresentation, movie: Movie) {
        MatcherAssert.assertThat(moviePresentation.id, CoreMatchers.`is`(movie.id))
        MatcherAssert.assertThat(moviePresentation.name, CoreMatchers.`is`(movie.name))
        MatcherAssert.assertThat(moviePresentation.title, CoreMatchers.`is`(movie.title))
        MatcherAssert.assertThat(moviePresentation.voteAverage, CoreMatchers.`is`(movie.voteAverage))
        MatcherAssert.assertThat(moviePresentation.profilePath, CoreMatchers.`is`(movie.profilePath))
        MatcherAssert.assertThat(moviePresentation.posterPath, CoreMatchers.`is`(movie.posterPath))
    }
}