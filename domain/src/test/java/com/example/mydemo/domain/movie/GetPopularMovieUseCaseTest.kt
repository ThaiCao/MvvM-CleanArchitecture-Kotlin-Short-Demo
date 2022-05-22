package com.example.mydemo.domain.movie
import com.example.mydemo.domain.base.UseCaseTest
import com.example.mydemo.domain.models.movies.Movie
import com.example.mydemo.domain.repositories.IMovieRepository
import com.example.mydemo.domain.usecases.base.IFlowUseCase
import com.example.mydemo.domain.usecases.features.movie.GetMoviePopularUseCaseImpl
import com.example.mydemo.tooltest.autoWire
import com.example.mydemo.tooltest.mock
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class GetPopularMovieUseCaseTest : UseCaseTest(){

    private val movieRepository: IMovieRepository = mock()

    private val getMoviePopularUseCase: GetMoviePopularUseCaseImpl = autoWire()

    @Test
    fun `test get popular movie success`() = runBlockingTest {
        val expected: List<Movie> = emptyList()
        coEvery { movieRepository.getPopularMovies() } returns flow{
            emit(expected)
        }

        val params = IFlowUseCase.None()
        getMoviePopularUseCase( params = params).collect { result ->
            assert(result != null)
            assert(expected == result)
        }

        coVerify { movieRepository.getPopularMovies() }

    }

    @Test
    fun `test get popular movie fail`() = runBlockingTest {
        coEvery { movieRepository.getPopularMovies() } returns flow{
            throw RuntimeException()
        }

        val params = IFlowUseCase.None()
        getMoviePopularUseCase( params = params).catch { exception ->
            assert(exception is RuntimeException)
        }

        coVerify { movieRepository.getPopularMovies() }

    }
}