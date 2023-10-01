package com.example.structure.presentation.base

import androidx.lifecycle.ViewModel
import com.example.structure.presentation.model.ErrorUi
import com.example.structure.presentation.model.NotificationUi
import com.example.structure.presentation.util.SingleLiveEvent
import com.github.ajalt.timberkt.Timber

abstract class BaseViewModel : ViewModel() {

    fun handleError(it: Throwable) {
        Timber.e { "$it" }
        error.value = ErrorUi.DefaultMessage
    }

    fun handleError(it: NotificationUi) {
        Timber.e { "$it" }
        topFloatingError.value = it
    }

    val error = SingleLiveEvent<ErrorUi>()
    val topFloatingError = SingleLiveEvent<NotificationUi>()
    val loading = SingleLiveEvent<Boolean>()

    fun showLoading() {
        if (loading.value == true) return
        loading.value = true
    }

    fun hideLoading() {
        if (loading.value == false) return
        loading.value = false
    }
}
