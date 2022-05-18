package com.example.mydemo.domain.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {
    val io: CoroutineDispatcher
}

class DispatcherProviderImpl : DispatcherProvider{
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

}