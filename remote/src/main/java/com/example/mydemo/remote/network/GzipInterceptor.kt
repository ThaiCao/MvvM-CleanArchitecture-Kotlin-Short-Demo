package com.example.mydemo.remote.network

import com.example.mydemo.remote.network.NetworkConfigs.Keys.ACCEPT_ENCODING
import com.example.mydemo.remote.network.NetworkConfigs.Keys.CONTENT_ENCODING
import com.example.mydemo.remote.network.NetworkConfigs.Values.ACCEPT_ENCODING_GZIP
import com.example.mydemo.remote.network.NetworkConfigs.Values.CONTENT_ENCODING_GZIP
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.GzipSource
import okio.buffer
import java.io.IOException

class GzipInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest: Request.Builder = chain.request().newBuilder()
        newRequest.addHeader(ACCEPT_ENCODING, ACCEPT_ENCODING_GZIP)
        val response: Response = chain.proceed(newRequest.build())
        return if (isGzipped(response)) {
            unzip(response)
        } else {
            response
        }
    }

    @Throws(IOException::class)
    private fun unzip(response: Response): Response {
        return response.body?.let {
            if (response.body == null) {
                return response
            }
            val gzipSource = GzipSource(it.source())
            val bodyString: String = gzipSource.buffer().readUtf8()
            val responseBody = bodyString.toResponseBody(it.contentType())
            val strippedHeaders = response.headers.newBuilder()
                .build()

            return response.newBuilder()
                .headers(strippedHeaders)
                .body(responseBody)
                .message(response.message)
                .build()
        } ?: response
    }

    private fun isGzipped(response: Response): Boolean {
        return response.header(CONTENT_ENCODING).equals(CONTENT_ENCODING_GZIP)
    }
}
