package com.example.structure.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in I, O>(private val dispatcherProvider: DispatcherProvider) {

    operator fun invoke(parameters: I) : Flow<Result<O>> {
        return execute(parameters)
            .catch {  emit(Result.failure(it)) }
            .flowOn(dispatcherProvider.io)
    }

    abstract fun execute(parameters: I) : Flow<Result<O>>
}
