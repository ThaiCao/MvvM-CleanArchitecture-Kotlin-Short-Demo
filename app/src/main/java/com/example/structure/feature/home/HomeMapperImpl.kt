package com.example.structure.feature.home

import com.example.structure.R
import com.example.structure.model.domain.HomeMenu
import com.example.structure.model.domain.HotMenu
import com.example.structure.model.domain.NewMenu
import com.example.structure.model.presentation.HomeItemUi
import com.example.structure.presentation.feature.home.HomeMapper

class HomeMapperImpl: HomeMapper {
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
            secondarySpace
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
            secondarySpace
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
            secondarySpace
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
