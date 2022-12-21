package com.example.structure.presentation.feature.splash

import com.example.structure.presentation.base.BaseViewModel
import com.example.structure.presentation.util.SingleLiveEvent
import kotlinx.coroutines.delay

class SplashViewModel : BaseViewModel() {
    val goToMain = SingleLiveEvent<Unit>()

    suspend fun init() {
        delay(3000)
        goToMain.call()
    }
}
