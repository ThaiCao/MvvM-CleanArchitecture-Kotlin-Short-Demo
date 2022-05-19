package com.example.mydemo.domain.usecases.base

import kotlinx.coroutines.flow.Flow

interface IFlowUseCase <out R : Any, in P> {
    suspend operator fun invoke(params: P): Flow<R>
    class None
}
