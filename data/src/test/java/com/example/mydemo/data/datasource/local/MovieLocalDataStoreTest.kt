package com.example.mydemo.data.datasource.local

import com.example.mydemo.data.base.DataTest
import com.example.mydemo.data.datasource.movie.local.db.IMovieLocalStore
import com.example.mydemo.data.datasource.movie.local.db.MovieLocalDataStore
import com.example.mydemo.data.datasource.movie.local.sharedpreference.MovieSharedPreferenceDataStore
import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.data.repository.MovieRepositoryImpl
import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.tooltest.autoWire
import com.example.mydemo.tooltest.mock
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class MovieLocalDataStoreTest : DataTest(){
    private val movieLocal: IMovieLocalStore  = mock()
    private val movieSharedPreference: MovieSharedPreferenceDataStore  = mock()
    private val localDataStore: MovieLocalDataStore = autoWire()

    @Test
    fun `get movie success`() = runBlockingTest {
        // Given
        coEvery { movieLocal.getPopularsMovies() } returns listMovieEntity

        // When
        var result: List<MovieEntity>? =  localDataStore.getPopularsMovies()

        // Then
        coVerify(exactly = 1) { movieLocal.getPopularsMovies() }
        MatcherAssert.assertThat(result, CoreMatchers.`is`( listMovieEntity))
    }

    @Test
    fun `test save movie`() = runBlockingTest {
        // Given

        coEvery { movieLocal.saveMovies(listMovieEntity) } just Runs

        //when
        localDataStore.saveMovies(listMovieEntity)
        // Then

    }

    @Test
    fun `get movie expired time success`() = runBlockingTest {
        // Given
        val expiredTime = System.currentTimeMillis()
        coEvery { movieSharedPreference.getMovieCacheExpiredTime() } returns expiredTime

        // When
        var result: Long? =  localDataStore.getMovieExpiredTime()

        // Then
        coVerify(exactly = 1) { movieSharedPreference.getMovieCacheExpiredTime() }
        MatcherAssert.assertThat(result, CoreMatchers.`is`( expiredTime))
    }

    @Test
    fun `test save movie expired time`() = runBlockingTest {
        // Given
        val expiredTime = System.currentTimeMillis()
        coEvery { movieSharedPreference.saveMovieCacheExpiredTime(expiredTime) } just Runs

        //when
        localDataStore.saveMovieExpiredTime(expiredTime)
        // Then

    }

    private val listMovieEntity: List<MovieEntity> = listOf(
        MovieEntity(name = "The Lost City", id = 752623, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Lost City", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieEntity(name = "Morbius", id = 752624, posterPath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", title = "Morbius", profilePath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", voteAverage = 3.8),
        MovieEntity(name = "The Northman", id = 752625, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Northman", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieEntity(name = "The Exorcism of God", id = 752626, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Exorcism of God", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
    )
}