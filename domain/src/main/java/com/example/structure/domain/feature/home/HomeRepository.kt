package com.example.structure.domain.feature.home

import com.example.structure.model.domain.*
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getHomeMenu(apiKey: String): Flow<Result<List<HomeMenu>>>

    fun getHomeHot(apiKey: String): Flow<Result<List<HotMenu>>>

    fun getHomeNew(apiKey: String): Flow<Result<List<NewMenu>>>
}
