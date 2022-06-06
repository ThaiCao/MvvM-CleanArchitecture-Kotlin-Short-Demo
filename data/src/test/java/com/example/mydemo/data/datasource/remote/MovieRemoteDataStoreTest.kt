package com.example.mydemo.data.datasource.remote

import com.example.mydemo.data.base.DataTest
import com.example.mydemo.data.datasource.movie.remote.IMovieRemoteStore
import com.example.mydemo.data.datasource.movie.remote.MovieRemoteDataStore
import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.tooltest.autoWire
import com.example.mydemo.tooltest.mock
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class MovieRemoteDataStoreTest : DataTest(){
    private val movieRemote: IMovieRemoteStore = mock()
    private val remoteDataStore: MovieRemoteDataStore = autoWire()

    @Test
    fun `get movie success`() = runBlockingTest {
        // Given
        coEvery { movieRemote.getPopularsMovies() } returns listMovieEntity

        // When
        var result: List<MovieEntity>? =  remoteDataStore.getPopularsMovies()

        // Then
        coVerify(exactly = 1) { movieRemote.getPopularsMovies() }
        MatcherAssert.assertThat(result, CoreMatchers.`is`( listMovieEntity))
    }

    private val listMovieEntity: List<MovieEntity> = listOf(
        MovieEntity(name = "The Lost City", id = 752623, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Lost City", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieEntity(name = "Morbius", id = 752624, posterPath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", title = "Morbius", profilePath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", voteAverage = 3.8),
        MovieEntity(name = "The Northman", id = 752625, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Northman", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieEntity(name = "The Exorcism of God", id = 752626, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Exorcism of God", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
    )
}
