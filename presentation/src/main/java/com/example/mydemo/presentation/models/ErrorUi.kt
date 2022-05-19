package com.example.mydemo.presentation.models

sealed class ErrorUi {

    data class Message(val title: String, val message: String) : ErrorUi()

    object LostInternetConnection : ErrorUi()

    object DefaultMessage : ErrorUi()
}
