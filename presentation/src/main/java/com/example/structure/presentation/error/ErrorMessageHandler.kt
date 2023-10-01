package com.example.structure.presentation.error

import com.example.structure.presentation.model.ErrorUi


interface ErrorMessageHandler {

    fun resolve(throwable: Throwable): ErrorUi
}
