package com.example.structure.network.feature.home

import com.example.structure.network.dto.MovieResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("/v1/movies")
    fun getConfig(
        @Query("category") category: String,
    ): Flow<MovieResponse>
}
