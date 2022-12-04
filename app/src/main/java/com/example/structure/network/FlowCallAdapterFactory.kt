package com.example.structure.network

import com.example.structure.data.response.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowCallAdapterFactory(
    private val apiResponseHandler: ApiResponseHandler,
    private val networkConnection: NetworkConnection,
    private val apiErrorParser: ApiErrorParser
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Flow::class.java != getRawType(returnType)) {
            return null
        }
        check(returnType is ParameterizedType) {
            "Flow return type must be parameterized as Flow<ApiResponse<Foo>>"
        }
        val responseType = getParameterUpperBound(0, returnType)

        val rawDeferredType = getRawType(responseType)

        return if (rawDeferredType == ApiResponse::class.java) {
            check(responseType is ParameterizedType) {
                "Response must be parameterized as ApiResponse<Foo>"
            }
            ResponseCallAdapter<Any>(
                responseType = responseType, // getParameterUpperBound(0, responseType)
                apiResponseHandler = apiResponseHandler
            )
        } else {
            BodyCallAdapter<Any>(
                responseType = responseType,
                networkConnection = networkConnection,
                apiErrorParser = apiErrorParser
            )
        }
    }
}
