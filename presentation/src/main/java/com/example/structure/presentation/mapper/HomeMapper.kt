package com.example.structure.presentation.mapper

import com.example.structure.domain.model.HomeMenu
import com.example.structure.domain.model.HotMenu
import com.example.structure.domain.model.NewMenu
import com.example.structure.presentation.R
import com.example.structure.presentation.model.HomeItemUi

interface HomeMapper {
    fun toHomeDetailItems(homeMovie: List<HomeMenu>?, newMovie: List<NewMenu>?, hotMovie: List<HotMenu>?): List<HomeItemUi>
}

class HomeMapperImpl() : HomeMapper {
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
