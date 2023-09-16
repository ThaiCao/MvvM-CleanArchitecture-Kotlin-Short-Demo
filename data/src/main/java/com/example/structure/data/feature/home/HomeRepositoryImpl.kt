package com.example.structure.data.feature.home

import com.example.structure.data.mapper.HomeMapper
import com.example.structure.domain.feature.home.HomeRepository
import com.example.structure.model.domain.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeRepositoryImpl(
    private val homeService: HomeService,
    private val homeMapper: HomeMapper,
) : HomeRepository {
    override fun getHomeMenu(apiKey: String): Flow<Result<List<HomeMenu>>> {
        return homeService.getHomeMenu(apiKey)
            .map { hotMenus ->
                homeMapper.toHomeMenuItems(hotMenus.results)
            }
            .map {
                Result.success(it)
            }
    }

    override fun getHomeHot(apiKey: String): Flow<Result<List<HotMenu>>> {
        return homeService.getHomeHot(apiKey)
            .map { hotMenus ->
                homeMapper.toHotMenuItems(hotMenus.results)
            }
            .map {
                Result.success(it)
            }
    }

    override fun getHomeNew(apiKey: String): Flow<Result<List<NewMenu>>> {
        return homeService.getHomeNew(apiKey)
            .map { hotMenus ->
                homeMapper.toNewMenuItems(hotMenus.results)
            }
            .map {
                Result.success(it)
            }
    }


}
