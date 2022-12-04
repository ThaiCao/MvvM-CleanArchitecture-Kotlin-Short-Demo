package com.example.structure.presentation.mapper

import com.example.structure.domain.model.HomeMenu
import com.example.structure.presentation.model.HomeMenuUi

interface HomeMenuUiMapper {
    fun toHomeMenuUi(menu: HomeMenu?): HomeMenuUi
}

class HomeMenuUiMapperImpl(): HomeMenuUiMapper {
    override fun toHomeMenuUi(menu: HomeMenu?): HomeMenuUi {
        TODO("Not yet implemented")
    }

}
