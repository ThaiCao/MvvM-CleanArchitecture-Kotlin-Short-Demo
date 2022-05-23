package com.example.mydemo.presentation.viewmodels.movie

import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.domain.usecases.features.movie.IGetMoviePopularUseCase
import com.example.mydemo.presentation.base.PresentableTest
import com.example.mydemo.presentation.base.captureValues
import com.example.mydemo.presentation.mapper.movie.MoviePresentationMapper
import com.example.mydemo.presentation.models.movie.IMovieItemUi
import com.example.mydemo.tooltest.autoWire
import com.example.mydemo.tooltest.mock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test

class PopularMovieViewModelTest: PresentableTest() {
    private val getMoviePopular: IGetMoviePopularUseCase = mock()
    private val mapper: MoviePresentationMapper = mock()

    private val viewModel: PopularMovieViewModel = autoWire()



    @Test
    fun `fetch popular movie success`() = runBlockingTest{
        // GIVEN
        coEvery { getMoviePopular(any()) } returns flow{
            emit(listMovie)
        }
        coEvery { mapper.mapToPresentation(listMovie[0]) } returns listMoviePresentation[0]
        coEvery { mapper.mapToPresentation(listMovie[1]) } returns listMoviePresentation[1]
        coEvery { mapper.mapToPresentation(listMovie[2]) } returns listMoviePresentation[2]
        coEvery { mapper.mapToPresentation(listMovie[3]) } returns listMoviePresentation[3]

        // WHEN
        viewModel.fetchPopularMovies()
        val movies = viewModel.popularMovies.captureValues()
        // THEN
        coVerify(exactly = 1) { getMoviePopular(any()) }
        assert(movies.size == 1)
        assert(movies[0]?.size == 4)
        val actualList = movies[0]!!
        MatcherAssert.assertThat(actualList,CoreMatchers.`is`(listMoviePresentation))
        MatcherAssert.assertThat(actualList[0], CoreMatchers.`is`(listMoviePresentation[0]))
        MatcherAssert.assertThat(actualList[1], CoreMatchers.`is`(listMoviePresentation[1]))
        MatcherAssert.assertThat(actualList[2], CoreMatchers.`is`(listMoviePresentation[2]))
        MatcherAssert.assertThat(actualList[3], CoreMatchers.`is`(listMoviePresentation[3]))
    }

    private val listMovie: List<Movie> = listOf(
        Movie(name = "The Lost City", id = 752623, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Lost City", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        Movie(name = "Morbius", id = 752624, posterPath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", title = "Morbius", profilePath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", voteAverage = 3.8),
        Movie(name = "The Northman", id = 752625, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Northman", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        Movie(name = "The Exorcism of God", id = 752626, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Exorcism of God", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
    )

    private val listMoviePresentation: List<IMovieItemUi.MoviePresentation> = listOf(
        IMovieItemUi.MoviePresentation(name = "The Lost City", id = 752623, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Lost City", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        IMovieItemUi.MoviePresentation(name = "Morbius", id = 752624, posterPath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", title = "Morbius", profilePath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", voteAverage = 3.8),
        IMovieItemUi.MoviePresentation(name = "The Northman", id = 752625, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Northman", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        IMovieItemUi.MoviePresentation(name = "The Exorcism of God", id = 752626, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Exorcism of God", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
    )
}