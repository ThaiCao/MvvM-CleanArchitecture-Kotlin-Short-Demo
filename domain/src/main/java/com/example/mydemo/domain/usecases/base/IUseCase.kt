package com.example.mydemo.domain.usecases.base

import com.example.mydemo.domain.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface IUseCase<out Type : Any, in Params> {

    val dispatcherProvider: DispatcherProvider

    fun run(params: Params): Flow<Result<Type>>

    operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Result<Type>) -> Unit = {}
    ) {

        scope.launch {
            try {
                withContext(dispatcherProvider.io) { run(params) }
                    .catch {
                        emit(Result.failure(it))
                    }
                    .flowOn(dispatcherProvider.io).cancellable()
                    .collect {
                        onResult(it)
                    }
            } catch (ex: Throwable) {

                print("ERROR: ${this@IUseCase.javaClass.toString()} throw $ex")

                onResult(Result.failure(ex))
            }
        }
    }

    class None
}