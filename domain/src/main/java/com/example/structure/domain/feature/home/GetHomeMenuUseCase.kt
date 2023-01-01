package com.example.structure.domain.feature.home

import com.example.structure.model.domain.HomeMenu
import com.example.structure.domain.usecase.UseCase

interface GetHomeMenuUseCase : UseCase<List<HomeMenu>, GetHomeMenuUseCase.Params> {
    data class Params(
        val apiKey: String,
    )
}
