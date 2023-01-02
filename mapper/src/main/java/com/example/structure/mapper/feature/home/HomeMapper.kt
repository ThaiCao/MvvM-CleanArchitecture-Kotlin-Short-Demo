package com.example.structure.mapper.feature.home

import com.example.structure.mapper.R
import com.example.structure.model.data.HomeMenuDto
import com.example.structure.model.data.HotMenuDto
import com.example.structure.model.data.NewMenuDto
import com.example.structure.model.domain.HomeMenu
import com.example.structure.model.domain.HotMenu
import com.example.structure.model.domain.NewMenu
import com.example.structure.model.presentation.HomeItemUi

interface HomeMapper {
    fun toHomeMenuItems(
        menuDtos: List<HomeMenuDto>?,
    ): List<HomeMenu>

    fun toHotMenuItems(
        menuDtos: List<HotMenuDto>,
    ): List<HotMenu>

    fun toNewMenuItems(
        menuDtos: List<NewMenuDto>,
    ): List<NewMenu>

    fun toHomeDetailItems(homeMovie: List<HomeMenu>?, newMovie: List<NewMenu>?, hotMovie: List<HotMenu>?): List<HomeItemUi>
}

class HomeMapperImpl() : HomeMapper {

    override fun toHomeMenuItems(menuDtos: List<HomeMenuDto>?): List<HomeMenu> {
        val menuItems = mutableListOf<HomeMenu>()
        menuDtos?.forEach { item ->
            menuItems.add(HomeMenu(
                id= item.id,
                name= item.name,
                imageUrl= item.imageUrl
            ))
        }
        return menuItems
    }

    override fun toHotMenuItems(menuDtos: List<HotMenuDto>): List<HotMenu> {
        val menuItems = mutableListOf<HotMenu>()
        menuDtos.forEach { item ->
            menuItems.add(HotMenu(
                id= item.id,
                name= item.name,
                imageUrl= item.imageUrl
            ))
        }
        return menuItems
    }

    override fun toNewMenuItems(menuDtos: List<NewMenuDto>): List<NewMenu> {
        val menuItems = mutableListOf<NewMenu>()
        menuDtos.forEach { item ->
            menuItems.add(NewMenu(
                id= item.id,
                name= item.name,
                imageUrl= item.imageUrl
            ))
        }
        return menuItems
    }

    private val primarySpace = HomeItemUi.SpaceItem(colorAttr = R.attr.colorOnPrimary)
    private val secondarySpace = HomeItemUi.SpaceItem(colorAttr = R.attr.colorOnSecondary)

    private fun toItemMenuRows(menuItems: List<HomeMenu>): List<HomeItemUi.PopularMovieRowItem> {
        return menuItems.map{
            HomeItemUi.PopularMovieRowItem(
                id = it.id,
                imageUrl = it.imageUrl,
                name = it.name
            )
        }
    }

    private fun getHomeMenuItems(
        menuItems: List<HomeMenu>?,
    ): List<HomeItemUi> {
        if (menuItems.isNullOrEmpty()) return emptyList()
        return listOf(
            HomeItemUi.PopularMovieItem(toItemMenuRows(menuItems)),
            primarySpace
        )
    }

    private fun toItemHotRows(hotItems: List<HotMenu>): List<HomeItemUi.HotMovieRowItem> {
        return hotItems.map{
            HomeItemUi.HotMovieRowItem(
                id = it.id,
                imageUrl = it.imageUrl,
                name = it.name
            )
        }
    }

    private fun getHomeHotItems(
        menuItems: List<HotMenu>?,
    ): List<HomeItemUi> {
        if (menuItems.isNullOrEmpty()) return emptyList()
        return listOf(
            HomeItemUi.HotMovieItem(toItemHotRows(menuItems)),
            primarySpace
        )
    }

    private fun toItemNewRows(newItems: List<NewMenu>): List<HomeItemUi.NewMovieRowItem> {
        return newItems.map{
            HomeItemUi.NewMovieRowItem(
                id = it.id,
                imageUrl = it.imageUrl,
                name = it.name
            )
        }
    }

    private fun getHomeNewItems(
        menuItems: List<NewMenu>?,
    ): List<HomeItemUi> {
        if (menuItems.isNullOrEmpty()) return emptyList()
        return listOf(
            HomeItemUi.NewMovieItem(toItemNewRows(menuItems)),
            primarySpace
        )
    }

    override fun toHomeDetailItems(
        homeMovie: List<HomeMenu>?,
        newMovie: List<NewMenu>?,
        hotMovie: List<HotMenu>?
    ): List<HomeItemUi> {
        val items = mutableListOf<HomeItemUi>()
        homeMovie?.let {
            items.addAll(getHomeMenuItems(it))
        }
        hotMovie?.let {
            items.addAll(getHomeHotItems(it))
        }
        newMovie?.let {
            items.addAll(getHomeNewItems(it))
        }
        return items
    }
}
