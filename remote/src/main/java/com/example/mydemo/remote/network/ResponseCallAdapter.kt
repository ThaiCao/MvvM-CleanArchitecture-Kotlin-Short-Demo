package com.example.mydemo.remote.network

import com.example.mydemo.remote.exception.Failure
import com.example.mydemo.remote.response.ApiResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResponseCallAdapter<T>(
    private val responseType: Type,
    private val apiResponseHandler: ApiResponseHandler
) : CallAdapter<ApiResponse<T>, Flow<ApiResponse<T>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<ApiResponse<T>>): Flow<ApiResponse<T>> =
        flow {
            emit(apiResponseHandler.handle(call))
        }.retryWhen { cause, attempt ->
            if (cause is Failure.NetworkError && attempt < 3) {
                delay(2000)
                true
            } else {
                false
            }
        }
}
