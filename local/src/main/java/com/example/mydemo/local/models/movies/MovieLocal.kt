package com.example.mydemo.local.models.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mydemo.local.utils.LocalConstants

@Entity(tableName = LocalConstants.MOVIE_TABLE_NAME)
data class MovieLocal (
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    var movieId: Long,

    @ColumnInfo(name = "title")
    var title: String? = "",

    @ColumnInfo(name = "name")
    var name: String? = "",

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double? = 0.0,

    @ColumnInfo(name = "profile_path")
    var profilePath: String? = "",

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = "",
)
