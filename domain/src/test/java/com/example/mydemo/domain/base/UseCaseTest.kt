package com.example.mydemo.domain.base
import com.example.mydemo.domain.dispatcher.DispatcherProvider
import com.example.mydemo.tooltest.BaseTest

abstract class UseCaseTest : BaseTest() {
    init {
        getKoin().declare(
            InstantCoroutineDispatchers(),
            secondaryTypes = listOf(DispatcherProvider::class)
        )
    }
}
