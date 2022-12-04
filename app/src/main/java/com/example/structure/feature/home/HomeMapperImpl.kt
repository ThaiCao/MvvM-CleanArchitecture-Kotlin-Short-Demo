package com.example.structure.feature.home

import com.example.structure.domain.model.HomeMenu
import com.example.structure.domain.model.HotMenu
import com.example.structure.domain.model.NewMenu
import com.example.structure.presentation.feature.home.HomeMapper
import com.example.structure.presentation.model.ItemHomeMenuRow
import com.example.structure.presentation.model.ItemHotRow
import com.example.structure.presentation.model.ItemNewRow

class HomeMapperImpl: HomeMapper {
    override fun toItemMenuRows(menuItems: List<HomeMenu>): List<ItemHomeMenuRow> {
        return menuItems.map{
            ItemHomeMenuRow(
                id = it.id,
                img = it.imageUrl,
                name = it.name
            )
        }
    }

    override fun toItemHotRows(hotItems: List<HotMenu>): List<ItemHotRow> {
        return hotItems.map{
            ItemHotRow(
                id = it.id,
                img = it.imageUrl,
                name = it.name
            )
        }
    }

    override fun toItemNewRows(newItems: List<NewMenu>): List<ItemNewRow> {
        return newItems.map{
            ItemNewRow(
                id = it.id,
                img = it.imageUrl,
                name = it.name
            )
        }
    }

}
