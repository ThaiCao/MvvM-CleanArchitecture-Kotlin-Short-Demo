package com.example.structure.domain.feature.home

import com.example.structure.model.domain.NewMenu
import com.example.structure.domain.usecase.DispatcherProvider
import kotlinx.coroutines.flow.Flow

class GetHomeNewUseCaseImpl(
    override val dispatcherProvider: DispatcherProvider,
    private val homeRepository: HomeRepository
): GetHomeNewUseCase {
    override fun run(params: GetHomeNewUseCase.Params): Flow<Result<List<NewMenu>>> {
        return homeRepository.getHomeNew(params.apiKey)
    }
}
