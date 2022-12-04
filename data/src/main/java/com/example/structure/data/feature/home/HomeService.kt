package com.example.structure.data.feature.home

import com.example.structure.data.model.HomeMenuDto
import com.example.structure.data.model.HotMenuDto
import com.example.structure.data.model.NewMenuDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("/3/movie/popular")
    fun getHomeMenu(
        @Query("api_key") apiKey: String,
    ): Flow<List<HomeMenuDto>>

    @GET("/3/movie/upcoming")
    fun getHomeHot(
        @Query("api_key") apiKey: String,
    ): Flow<List<HotMenuDto>>

    @GET("/3/movie/now_playing")
    fun getHomeNew(
        @Query("api_key") apiKey: String,
    ): Flow<List<NewMenuDto>>
}

