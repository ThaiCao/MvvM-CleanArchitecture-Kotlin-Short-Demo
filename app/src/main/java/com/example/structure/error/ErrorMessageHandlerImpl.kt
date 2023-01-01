package com.example.structure.error

import com.example.structure.R
import com.example.structure.domain.exception.Failure
import com.example.structure.model.presentation.ErrorUi
import com.example.structure.presentation.error.ErrorMessageHandler
import com.example.structure.uibase.extend.StringRes

class ErrorMessageHandlerImpl(private val stringRes: StringRes) : ErrorMessageHandler {
    override fun resolve(throwable: Throwable): ErrorUi {
        return when (throwable) {
            is Failure.ApiError -> ErrorUi.Message(
                title = stringRes.getString(R.string.oops),
                message = throwable.message.toString()
            )
            is Failure.NetworkError -> ErrorUi.LostInternetConnection
            else -> ErrorUi.Message(
                title = stringRes.getString(R.string.oops),
                message = stringRes.getString(R.string.error_general_error_message)
            )
        }
    }
}
