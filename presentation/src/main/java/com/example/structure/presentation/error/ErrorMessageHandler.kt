package com.example.structure.presentation.error

import com.example.structure.model.presentation.ErrorUi

interface ErrorMessageHandler {

    fun resolve(throwable: Throwable): ErrorUi
}
