package com.example.mydemo.remote.datasource
import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.remote.apiservices.IMovieServiceApi
import com.example.mydemo.remote.mapper.movie.MovieRemoteMapper
import com.example.mydemo.remote.models.movies.MovieListModel
import com.example.mydemo.remote.models.movies.MovieModel
import com.example.mydemo.tooltest.BaseTest

import com.example.mydemo.tooltest.autoWire
import com.example.mydemo.tooltest.mock
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class MovieRemoteImplTest : BaseTest(){
    private val moviesService: IMovieServiceApi = mock()
    private val movieRemoteMapper: MovieRemoteMapper = mock()
    private val movieRemoteImpl: MovieRemoteImpl = autoWire()

    @Test
    fun `test get movie remote`() = runBlockingTest {
        // Given
        val movieListModel = MovieListModel(listOfMoviesResponse = listMovie)
        coEvery { moviesService.getPopularsMovies() } returns movieListModel
        coEvery { movieRemoteMapper.mapFromApiResponseModel(movieListModel.listOfMoviesResponse[0]) } returns listMovieEntity[0]
        coEvery { movieRemoteMapper.mapFromApiResponseModel(movieListModel.listOfMoviesResponse[1]) } returns listMovieEntity[1]
        coEvery { movieRemoteMapper.mapFromApiResponseModel(movieListModel.listOfMoviesResponse[2]) } returns listMovieEntity[2]
        coEvery { movieRemoteMapper.mapFromApiResponseModel(movieListModel.listOfMoviesResponse[3]) } returns listMovieEntity[3]

        //when
        val result = movieRemoteImpl.getPopularsMovies()

        // Then
        coVerify(exactly = 1) { moviesService.getPopularsMovies() }
        MatcherAssert.assertThat(result.size, CoreMatchers.`is`(listMovieEntity.size))
        MatcherAssert.assertThat(result[0], CoreMatchers.`is`(listMovieEntity[0]))
        MatcherAssert.assertThat(result[1], CoreMatchers.`is`(listMovieEntity[1]))
        MatcherAssert.assertThat(result[2], CoreMatchers.`is`(listMovieEntity[2]))
        MatcherAssert.assertThat(result[3], CoreMatchers.`is`(listMovieEntity[3]))
    }

    private val listMovieEntity: List<MovieEntity> = listOf(
        MovieEntity(name = "The Lost City", id = 752623, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Lost City", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieEntity(name = "Morbius", id = 752624, posterPath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", title = "Morbius", profilePath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", voteAverage = 3.8),
        MovieEntity(name = "The Northman", id = 752625, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Northman", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieEntity(name = "The Exorcism of God", id = 752626, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Exorcism of God", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
    )

    private val listMovie: List<MovieModel> = listOf(
        MovieModel(name = "The Lost City", id = 752623, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Lost City", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieModel(name = "Morbius", id = 752624, posterPath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", title = "Morbius", profilePath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", voteAverage = 3.8),
        MovieModel(name = "The Northman", id = 752625, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Northman", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieModel(name = "The Exorcism of God", id = 752626, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Exorcism of God", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
    )
}