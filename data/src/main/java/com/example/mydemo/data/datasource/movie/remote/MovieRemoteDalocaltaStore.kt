package com.example.mydemo.data.datasource.movie.remote

import com.example.mydemo.data.datasource.movie.IMovieDataStore
import com.example.mydemo.data.datasource.movie.local.sharedpreference.MovieSharedPreferenceDataStore
import com.example.mydemo.data.models.MovieEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

class MovieRemoteDalocaltaStore(
    private val movieRemote: IMovieRemoteStore,
) : IMovieDataStore {
    override suspend fun getPopularsMovies(): List<MovieEntity> {
        return movieRemote.getPopularsMovies()
    }

    override suspend fun getPopularsMoviesWithCache(): List<MovieEntity> {
        return movieRemote.getPopularsMoviesAsync()
    }

    override suspend fun saveMovies(listMovies: List<MovieEntity>) {
        throw UnsupportedOperationException("save movies action not applicable for remote.")
    }

    override fun saveMovieExpiredTime(expiredTime: Long) {
        throw UnsupportedOperationException("save movies expired time action not applicable for remote.")
    }

    override fun getMovieExpiredTime(): Long? {
        throw UnsupportedOperationException("get movies expired time action not applicable for remote.")
    }
}
