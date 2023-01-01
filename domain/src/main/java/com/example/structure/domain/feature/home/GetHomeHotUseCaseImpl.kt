package com.example.structure.domain.feature.home

import com.example.structure.model.domain.HotMenu
import com.example.structure.domain.usecase.DispatcherProvider
import kotlinx.coroutines.flow.Flow

class GetHomeHotUseCaseImpl(
    override val dispatcherProvider: DispatcherProvider,
    private val homeRepository: HomeRepository
): GetHomeHotUseCase {
    override fun run(params: GetHomeHotUseCase.Params): Flow<Result<List<HotMenu>>> {
        return homeRepository.getHomeHot(params.apiKey)
    }
}
