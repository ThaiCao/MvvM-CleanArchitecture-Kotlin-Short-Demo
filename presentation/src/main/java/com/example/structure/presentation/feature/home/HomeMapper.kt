package com.example.structure.presentation.feature.home

import com.example.structure.domain.model.HomeMenu
import com.example.structure.domain.model.HotMenu
import com.example.structure.domain.model.NewMenu
import com.example.structure.presentation.model.HomeItemUi

interface HomeMapper {

//    fun toItemMenuRows(menuItems: List<HomeMenu>): List<HomeItemUi.PopularMovieRowItem>
//
//    fun toItemHotRows(hotItems: List<HotMenu>): List<HomeItemUi.HotMovieRowItem>
//
//    fun toItemNewRows(newItems: List<NewMenu>): List<HomeItemUi.NewMovieRowItem>

    fun toHomeDetailItems(homeMovie: List<HomeMenu>?, newMovie: List<NewMenu>?, hotMovie: List<HotMenu>?): List<HomeItemUi>
}
