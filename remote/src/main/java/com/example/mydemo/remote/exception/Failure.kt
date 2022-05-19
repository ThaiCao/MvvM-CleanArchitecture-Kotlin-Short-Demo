package com.example.mydemo.remote.exception

sealed class Failure(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    data class BodyEmpty(val msg: String? = null) : Failure()

    data class ApiError(
        val responseCode: Int? = null,
        val code: String? = null,
        val msg: String? = null
    ) : Failure(message = msg)

    object NetworkError : Failure()

    object TokenExpired : Failure()

    data class ParamsInvalid(val msg: String?) : Failure(message = msg)

    data class UnknownError(val error: Throwable? = null) : Failure(cause = error)

    open class BusinessFailure(
        message: String? = null,
        cause: Throwable? = null
    ) : Failure(
        message = message,
        cause = cause
    )


    sealed class PaymentException(
        message: String? = null,
        cause: Throwable? = null
    ) : Failure(message, cause) {
        data class PaymentError(val msg: String?) : PaymentException(msg)
    }

}
