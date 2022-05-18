package com.example.mydemo.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydemo.local.models.movies.MovieLocal

@Dao
interface IMovieLocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movieCache: MovieLocal)

    @Query("SELECT * FROM movies")
    fun getMovies(): List<MovieLocal>

    @Query("DELETE FROM movies")
    fun deleteMovies()
}