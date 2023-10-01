package com.example.structure.domain.feature.home

import com.example.structure.domain.model.NewMenu
import com.example.structure.domain.usecase.UseCase

interface GetHomeNewUseCase : UseCase<List<NewMenu>, GetHomeNewUseCase.Params> {
    data class Params(
        val apiKey: String,
    )
}
