package com.example.mydemo.local.datasource

import com.example.mydemo.data.datasource.movie.local.db.IMovieLocalStore
import com.example.mydemo.data.models.MovieEntity
import com.example.mydemo.local.dao.IMovieLocalDao
import com.example.mydemo.local.mapper.movie.MovieLocalMapper

class MovieLocalImpl(
    private val movieLocalDao: IMovieLocalDao,
    private val movieLocalMapper: MovieLocalMapper,
    ) : IMovieLocalStore {
    override suspend fun getPopularsMovies(): List<MovieEntity> {
        return movieLocalDao.getMovies().map{
            movieLocalMapper.mapFromLocal(it)
        }
    }

    override suspend fun saveMovies(listMovies: List<MovieEntity>) {
        listMovies.map{
            movieLocalMapper.mapToLocal(it)
        }.forEach {
            movieLocalDao.addMovie(it)
        }
    }

    override suspend fun isCached(): Boolean {
        return movieLocalDao.getMovies().isNotEmpty()
    }

    override fun isExpired(lastUpdateTime: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    companion object {
        /**
         * Expiration time set to 3 minutes
         */
        const val EXPIRATION_TIME = (60 * 3 * 1000).toLong()
    }
}