package com.example.mydemo.presentation.base

import androidx.lifecycle.ViewModel
import com.example.mydemo.presentation.models.ErrorUi
import com.example.mydemo.presentation.utils.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {

    fun handleError(it: Throwable) {
        it.printStackTrace()
        error.value = ErrorUi.DefaultMessage
    }

    val error = SingleLiveEvent<ErrorUi>()
    val loading = SingleLiveEvent<Boolean>()
}
