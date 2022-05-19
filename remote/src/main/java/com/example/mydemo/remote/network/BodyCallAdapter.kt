package com.example.mydemo.remote.network

import com.example.mydemo.remote.exception.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Invocation
import java.lang.reflect.Type
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class BodyCallAdapter<T : Any>(
    private val responseType: Type,
    private val networkConnection: NetworkConnection,
    private val apiErrorParser: ApiErrorParser
) : CallAdapter<T, Flow<T>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T>): Flow<T> = flow {
        if (!networkConnection.isConnected()) throw Failure.NetworkError
        emit(awaitBody(call))
    }

    private suspend fun <T> awaitBody(call: Call<T>): T {
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

            when (val body = response.body()) {
                null -> {
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
                else -> {
                    continuation.resume(body)
                }
            }
        }
    }
}
