package com.example.structure.domain.exception

sealed class Failure(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause) {

    data class BodyEmpty(val msg: String? = null) : Failure()

    open class ApiError(
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
        object PaymentCancel : PaymentException()
    }

}

sealed class APIException : Failure.ApiError() {
    // 400
    object InvalidApi: APIException()
    // 404 No account found
    object ApiNotFound: APIException()
    // 401 JWT invalid
    object TokenInvalid: APIException()
    // 500 System encountered an unexpected error
    object SystemError: APIException()
}
