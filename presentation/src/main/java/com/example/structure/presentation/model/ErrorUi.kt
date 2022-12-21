package com.example.structure.presentation.model

sealed class ErrorUi {

    data class Message(val title: String, val message: String) : ErrorUi()

    object LostInternetConnection : ErrorUi()

    object DefaultMessage : ErrorUi()
}
