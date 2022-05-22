package com.example.mydemo.domain.base

import com.example.mydemo.domain.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class InstantCoroutineDispatchers : DispatcherProvider {
    override val io: CoroutineDispatcher = UnconfinedTestDispatcher()
}
