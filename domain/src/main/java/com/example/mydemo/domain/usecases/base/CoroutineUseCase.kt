package com.example.mydemo.domain.usecases.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<out R, in P> {
    abstract val coroutineDispatcher: CoroutineDispatcher
    suspend operator fun invoke(params: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                Result.success(execute(params))
            }
        } catch (ex: Throwable) {
            Result.failure(ex)
        }
    }

    internal abstract suspend fun execute(params: P): R
}
