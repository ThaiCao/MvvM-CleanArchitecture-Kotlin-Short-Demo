package com.example.mydemo.remote.network

import com.example.mydemo.remote.exception.Failure
import com.example.mydemo.remote.response.ApiResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Invocation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface ApiResponseHandler {
    suspend fun <T> handle(call: Call<ApiResponse<T>>): ApiResponse<T>
}

class DefaultApiResponseHandler(
    private val networkConnection: NetworkConnection,
    private val apiErrorParser: ApiErrorParser
) : ApiResponseHandler {

    override suspend fun <T> handle(call: Call<ApiResponse<T>>): ApiResponse<T> {
        if (!networkConnection.isConnected()) throw Failure.NetworkError

        return awaitApiResponse(call)
    }

    private suspend fun <T> awaitApiResponse(call: Call<ApiResponse<T>>): ApiResponse<T> {
        return suspendCancellableCoroutine { continuation ->
            continuation.invokeOnCancellation {
                call.cancel()
            }

            val response = try {
                call.execute()
            } catch (t: Throwable) {
                continuation.resumeWithException(Failure.UnknownError(t))
                return@suspendCancellableCoroutine
            }

            if (!response.isSuccessful) {
                continuation.resumeWithException(apiErrorParser.parse(response))
                return@suspendCancellableCoroutine
            }

            val body = response.body()

            when {
                body?.data == null -> {
                    val invocation = call.request().tag(Invocation::class.java)!!
                    val method = invocation.method()
                    val e = Failure.BodyEmpty(
                        "Response from " +
                                method.declaringClass.name +
                                '.' +
                                method.name +
                                " was null but response body type was declared as non-null"
                    )
                    continuation.resumeWithException(e)
                }

                body.error != null -> {
                    continuation.resumeWithException(apiErrorParser.parse(body.error))
                }

                else -> {
                    continuation.resume(body)
                }
            }
        }
    }
}
