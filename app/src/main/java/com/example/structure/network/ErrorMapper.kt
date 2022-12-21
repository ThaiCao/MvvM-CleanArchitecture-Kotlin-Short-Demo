package com.example.structure.network

import com.example.structure.domain.exception.APIException
import com.example.structure.domain.exception.Failure

interface ErrorMapper {
    fun toApiError(error: Failure.ApiError): Failure.ApiError
}

class ErrorMapperImpl : ErrorMapper {
    override fun toApiError(error: Failure.ApiError): Failure.ApiError {
        return when (error.responseCode) {
            400 -> APIException.InvalidApi
            404 -> APIException.ApiNotFound
            401 -> APIException.TokenInvalid
            500 -> APIException.SystemError
            else -> error
        }
    }
}

