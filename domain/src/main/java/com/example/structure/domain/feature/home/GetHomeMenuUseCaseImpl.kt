package com.example.structure.domain.feature.home

import com.example.structure.domain.model.HomeMenu
import com.example.structure.domain.usecase.DispatcherProvider
import kotlinx.coroutines.flow.Flow

class GetHomeMenuUseCaseImpl(
    override val dispatcherProvider: DispatcherProvider,
    private val homeRepository: HomeRepository
): GetHomeMenuUseCase {
    override fun run(params: GetHomeMenuUseCase.Params): Flow<Result<List<HomeMenu>>> {
        return homeRepository.getHomeMenu(params.apiKey)
    }
}
