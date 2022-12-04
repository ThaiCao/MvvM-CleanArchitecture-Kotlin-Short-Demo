package com.example.structure.presentation.feature.home

import com.example.structure.domain.model.HomeMenu
import com.example.structure.domain.model.HotMenu
import com.example.structure.domain.model.NewMenu
import com.example.structure.presentation.model.ItemHomeMenuRow
import com.example.structure.presentation.model.ItemHotRow
import com.example.structure.presentation.model.ItemNewRow

interface HomeMapper {

    fun toItemMenuRows(menuItems: List<HomeMenu>): List<ItemHomeMenuRow>

    fun toItemHotRows(hotItems: List<HotMenu>): List<ItemHotRow>

    fun toItemNewRows(newItems: List<NewMenu>): List<ItemNewRow>
}
