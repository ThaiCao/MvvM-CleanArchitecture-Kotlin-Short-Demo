package com.example.structure.model.presentation

sealed class NotificationUi(open val message: CharSequence) {

    data class Info(override val message: CharSequence) : NotificationUi(message)

    data class Alert(override val message: CharSequence) : NotificationUi(message)

    data class Success(override val message: CharSequence) : NotificationUi(message)
}
