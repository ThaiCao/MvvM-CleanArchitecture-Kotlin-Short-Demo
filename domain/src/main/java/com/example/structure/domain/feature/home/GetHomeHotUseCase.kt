package com.example.structure.domain.feature.home

import com.example.structure.model.domain.HotMenu
import com.example.structure.domain.usecase.UseCase

interface GetHomeHotUseCase : UseCase<List<HotMenu>, GetHomeHotUseCase.Params> {
    data class Params(
        val apiKey: String,
    )
}
