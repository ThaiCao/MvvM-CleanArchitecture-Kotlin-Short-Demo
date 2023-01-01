package com.example.structure.model.presentation

sealed class ErrorUi {

    data class Message(val title: String, val message: String) : ErrorUi()

    object LostInternetConnection : ErrorUi()

    object DefaultMessage : ErrorUi()
}
