package com.example.mydemo.local.datasource

import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.local.dao.IMovieLocalDao
import com.example.mydemo.local.mapper.movie.MovieLocalMapper
import com.example.mydemo.local.models.movies.MovieLocal
import com.example.mydemo.tooltest.BaseTest

import com.example.mydemo.tooltest.autoWire
import com.example.mydemo.tooltest.mock
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class MovieLocalImplTest : BaseTest() {

    private val movieLocalDao: IMovieLocalDao  = mock()
    private val movieLocalMapper: MovieLocalMapper  = mock()
    private val movieLocalImpl: MovieLocalImpl = autoWire()

    @Test
    fun `test get movie local`() = runBlockingTest {
        // Given
        coEvery { movieLocalDao.getMovies() } returns listMovieLocal
        coEvery { movieLocalMapper.mapFromLocal(listMovieLocal[0]) } returns listMovieEntity[0]
        coEvery { movieLocalMapper.mapFromLocal(listMovieLocal[1]) } returns listMovieEntity[1]
        coEvery { movieLocalMapper.mapFromLocal(listMovieLocal[2]) } returns listMovieEntity[2]
        coEvery { movieLocalMapper.mapFromLocal(listMovieLocal[3]) } returns listMovieEntity[3]

        //when
        val result = movieLocalImpl.getPopularsMovies()

        // Then
        coVerify(exactly = 1) { movieLocalDao.getMovies() }
        MatcherAssert.assertThat(result.size, CoreMatchers.`is`(listMovieEntity.size))
        MatcherAssert.assertThat(result[0], CoreMatchers.`is`(listMovieEntity[0]))
        MatcherAssert.assertThat(result[1], CoreMatchers.`is`(listMovieEntity[1]))
        MatcherAssert.assertThat(result[2], CoreMatchers.`is`(listMovieEntity[2]))
        MatcherAssert.assertThat(result[3], CoreMatchers.`is`(listMovieEntity[3]))
    }

    @Test
    fun `test save movie`() = runBlockingTest {
        // Given
        coEvery { movieLocalMapper.mapToLocal(listMovieEntity[0]) } returns listMovieLocal[0]
        coEvery { movieLocalMapper.mapToLocal(listMovieEntity[1]) } returns listMovieLocal[1]
        coEvery { movieLocalMapper.mapToLocal(listMovieEntity[2]) } returns listMovieLocal[2]
        coEvery { movieLocalMapper.mapToLocal(listMovieEntity[3]) } returns listMovieLocal[3]
        coEvery {  movieLocalDao.addMovie(listMovieLocal[0]) } returns Unit
        coEvery {  movieLocalDao.addMovie(listMovieLocal[1]) } returns Unit
        coEvery {  movieLocalDao.addMovie(listMovieLocal[2]) } returns Unit
        coEvery {  movieLocalDao.addMovie(listMovieLocal[3]) } returns Unit

        //when
        movieLocalImpl.saveMovies(listMovieEntity)
        // Then

    }

    private val listMovieEntity: List<MovieEntity> = listOf(
        MovieEntity(name = "The Lost City", id = 752623, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Lost City", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieEntity(name = "Morbius", id = 752624, posterPath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", title = "Morbius", profilePath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", voteAverage = 3.8),
        MovieEntity(name = "The Northman", id = 752625, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Northman", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieEntity(name = "The Exorcism of God", id = 752626, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Exorcism of God", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
    )

    private val listMovieLocal: List<MovieLocal> = listOf(
        MovieLocal(name = "The Lost City", movieId = 752623, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Lost City", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieLocal(name = "Morbius", movieId = 752624, posterPath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", title = "Morbius", profilePath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", voteAverage = 3.8),
        MovieLocal(name = "The Northman", movieId = 752625, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Northman", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieLocal(name = "The Exorcism of God", movieId = 752626, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Exorcism of God", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
    )
}