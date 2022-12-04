package com.example.structure.domain.feature.home

import com.example.structure.domain.model.HomeMenu
import com.example.structure.domain.model.HotMenu
import com.example.structure.domain.model.NewMenu
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getHomeMenu(apiKey: String): Flow<Result<List<HomeMenu>>>

    fun getHomeHot(apiKey: String): Flow<Result<List<HotMenu>>>

    fun getHomeNew(apiKey: String): Flow<Result<List<NewMenu>>>
}
