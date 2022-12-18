package com.example.structure.data.feature.home

import com.example.structure.data.model.HomeMenuDto
import com.example.structure.data.model.HotMenuDto
import com.example.structure.data.model.NewMenuDto
import com.example.structure.domain.model.HomeMenu
import com.example.structure.domain.model.HotMenu
import com.example.structure.domain.model.NewMenu

interface HomeMapper {

    fun toHomeMenu(homeMenuDto: HomeMenuDto): HomeMenu

    fun toHotMenu(hotMenuDto: HotMenuDto): HotMenu

    fun toNewMenu(newMenuDto: NewMenuDto): NewMenu

    fun toHomeMenuItems(
        menuDtos: List<HomeMenuDto>?,
    ): List<HomeMenu>

    fun toHotMenuItems(
        menuDtos: List<HotMenuDto>,
    ): List<HotMenu>

    fun toNewMenuItems(
        menuDtos: List<NewMenuDto>,
    ): List<NewMenu>
}


class HomeMapperImpl() : HomeMapper {
    override fun toHomeMenu(homeMenuDto: HomeMenuDto): HomeMenu {
        return HomeMenu(
            id= homeMenuDto.id,
            name= homeMenuDto.name,
            imageUrl= homeMenuDto.imageUrl
        )
    }

    override fun toHotMenu(hotMenuDto: HotMenuDto): HotMenu {
        return HotMenu(
            id= hotMenuDto.id,
            name= hotMenuDto.name,
            imageUrl= hotMenuDto.imageUrl
        )
    }

    override fun toNewMenu(newMenuDto: NewMenuDto): NewMenu {
        return NewMenu(
            id= newMenuDto.id,
            name= newMenuDto.name,
            imageUrl= newMenuDto.imageUrl
        )
    }

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

}
