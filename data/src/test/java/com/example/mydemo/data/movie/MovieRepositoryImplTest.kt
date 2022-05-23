package com.example.mydemo.data.movie

import com.example.mydemo.data.base.DataTest
import com.example.mydemo.data.datasource.movie.local.db.MovieLocalDataStore
import com.example.mydemo.data.datasource.movie.local.sharedpreference.MovieSharedPreferenceDataStore
import com.example.mydemo.data.datasource.movie.remote.MovieRemoteDataStore
import com.example.mydemo.data.factory.IMovieDataStoreFactory
import com.example.mydemo.data.mapper.movie.MovieMapper
import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.data.repository.MovieRepositoryImpl
import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.tooltest.autoWire
import com.example.mydemo.tooltest.mock
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert
import org.junit.Test

class MovieRepositoryImplTest : DataTest() {

    private val movieMapper: MovieMapper = mock()
    private val movieDataStoreFactory: IMovieDataStoreFactory = mock()
    private val movieRemoteDataStore: MovieRemoteDataStore = mock()
    private val movieLocalDataStore: MovieLocalDataStore = mock()
    private val movieSharedPreferenceDataStore: MovieSharedPreferenceDataStore = mock()
    private val repo: MovieRepositoryImpl = autoWire()

    @Test
    fun `get movie from server success`() = runBlockingTest {
        // Given
        coEvery { movieDataStoreFactory.getDataStore() } returns movieRemoteDataStore
        coEvery { movieRemoteDataStore.getPopularsMovies() } returns listMovieEntity

        coEvery { movieDataStoreFactory.getLocalDataStore() } returns movieLocalDataStore
        coEvery { movieLocalDataStore.saveMovies(listMovieEntity) } returns Unit
        coEvery { movieDataStoreFactory.getMovieSharedPreferenceDataStore() } returns movieSharedPreferenceDataStore
        coEvery { movieSharedPreferenceDataStore.saveMovieCacheExpiredTime(any()) } returns Unit
        coEvery { movieMapper.mapFromEntity(listMovieEntity[0]) } returns listMovie[0]
        coEvery { movieMapper.mapFromEntity(listMovieEntity[1]) } returns listMovie[1]
        coEvery { movieMapper.mapFromEntity(listMovieEntity[2]) } returns listMovie[2]
        coEvery { movieMapper.mapFromEntity(listMovieEntity[3]) } returns listMovie[3]

        // When
        var result: List<Movie>? = null
        repo.getPopularMovies().collect { result = it }

        // Then
        coVerify(exactly = 1) { movieRemoteDataStore.getPopularsMovies() }
        MatcherAssert.assertThat(result, `is`( listMovie))
    }

    @Test
    fun `get movie from local success`() = runBlockingTest {
        // Given
        coEvery { movieDataStoreFactory.getDataStore() } returns movieLocalDataStore
        coEvery { movieLocalDataStore.getPopularsMovies() } returns listMovieEntity
        coEvery { movieDataStoreFactory.getLocalDataStore() } returns movieLocalDataStore
        coEvery { movieLocalDataStore.saveMovies(listMovieEntity) } returns Unit
        coEvery { movieDataStoreFactory.getMovieSharedPreferenceDataStore() } returns movieSharedPreferenceDataStore
        coEvery { movieSharedPreferenceDataStore.saveMovieCacheExpiredTime(any()) } returns Unit
        coEvery { movieMapper.mapFromEntity(listMovieEntity[0]) } returns listMovie[0]
        coEvery { movieMapper.mapFromEntity(listMovieEntity[1]) } returns listMovie[1]
        coEvery { movieMapper.mapFromEntity(listMovieEntity[2]) } returns listMovie[2]
        coEvery { movieMapper.mapFromEntity(listMovieEntity[3]) } returns listMovie[3]

        // When
        var result: List<Movie>? = null
        repo.getPopularMovies().collect { result = it }

        // Then
        coVerify(exactly = 1) { movieLocalDataStore.getPopularsMovies() }
        MatcherAssert.assertThat(result, `is`( listMovie))
    }

    private val listMovieEntity: List<MovieEntity> = listOf(
        MovieEntity(name = "The Lost City", id = 752623, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Lost City", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieEntity(name = "Morbius", id = 752624, posterPath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", title = "Morbius", profilePath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", voteAverage = 3.8),
        MovieEntity(name = "The Northman", id = 752625, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Northman", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        MovieEntity(name = "The Exorcism of God", id = 752626, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Exorcism of God", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
    )

    private val listMovie: List<Movie> = listOf(
        Movie(name = "The Lost City", id = 752623, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Lost City", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        Movie(name = "Morbius", id = 752624, posterPath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", title = "Morbius", profilePath= "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg", voteAverage = 3.8),
        Movie(name = "The Northman", id = 752625, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Northman", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
        Movie(name = "The Exorcism of God", id = 752626, posterPath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", title = "The Exorcism of God", profilePath= "/neMZH82Stu91d3iqvLdNQfqPPyl.jpg", voteAverage = 3.8),
    )
}