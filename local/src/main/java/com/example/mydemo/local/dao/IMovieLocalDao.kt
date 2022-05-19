package com.example.mydemo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydemo.local.models.movies.MovieLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface IMovieLocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movieCache: MovieLocal)

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieLocal>

    @Query("DELETE FROM movies")
    suspend fun deleteMovies()
}