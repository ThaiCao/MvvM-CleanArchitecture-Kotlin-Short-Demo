package com.example.structure.network

import com.example.structure.domain.exception.Failure
import com.github.ajalt.timberkt.Timber
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.lang.reflect.Type

interface ApiErrorParser {
    fun <T> parse(response: Response<T>): Failure.ApiError

    fun parse(error: Any?): Failure.ApiError
}

class ApiErrorParserImpl(private val errorMapper: ErrorMapper) : ApiErrorParser {

    override fun <T> parse(response: Response<T>): Failure.ApiError {
        val type: Type = object : TypeToken<HashMap<Any?, Any?>>() {}.type
        val errorMessageMap: HashMap<Any?, Any?> = Gson().fromJson(
            response.errorBody()?.charStream(),
            type
        )

        Timber.e { "$errorMessageMap" }

        return Failure.ApiError(
            responseCode = response.code(),
            msg = toMessage(errorMessageMap = errorMessageMap) ?: response.message(),
            code = toCode(errorMessageMap = errorMessageMap)
        ).let { error ->
            errorMapper.toApiError(error)
        }
    }

    override fun parse(error: Any?): Failure.ApiError {
        val map = error as? LinkedTreeMap<*, *>

        Timber.e { "$map" }

        return Failure.ApiError(
            code = map?.get("code")?.toString(),
            msg = map?.get("message")?.toString()
        )
    }

    private fun toCode(errorMessageMap: HashMap<Any?, Any?>?): String? {
        val error = errorMessageMap?.get("error") as? LinkedTreeMap<*, *> ?: return null
        return error["errorCode"]?.toString()
    }

    private fun toMessage(errorMessageMap: HashMap<Any?, Any?>?): String? {
        val error = errorMessageMap?.get("error") as? LinkedTreeMap<*, *> ?: return null
        return error["errorMessage"]?.toString()
    }
}

