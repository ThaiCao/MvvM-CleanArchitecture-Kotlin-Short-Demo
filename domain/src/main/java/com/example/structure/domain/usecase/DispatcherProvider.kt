package com.example.structure.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {
    val io: CoroutineDispatcher
}

class DispatcherProviderImpl : DispatcherProvider {
    override val io = Dispatchers.IO
}
