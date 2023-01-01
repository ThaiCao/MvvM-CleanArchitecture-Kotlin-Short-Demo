package com.example.structure.data.feature.home

import com.example.structure.model.data.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("/3/movie/popular")
    fun getHomeMenu(
        @Query("api_key") apiKey: String,
    ): Flow<MovieMenuResponseDto>

    @GET("/3/movie/upcoming")
    fun getHomeHot(
        @Query("api_key") apiKey: String,
    ): Flow<MovieHotResponseDto>

    @GET("/3/movie/now_playing")
    fun getHomeNew(
        @Query("api_key") apiKey: String,
    ): Flow<MovieNewResponseDto>
}

