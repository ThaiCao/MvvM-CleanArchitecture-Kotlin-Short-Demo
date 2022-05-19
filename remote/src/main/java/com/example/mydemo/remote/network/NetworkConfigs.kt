package com.example.mydemo.remote.network

import com.example.mydemo.remote.BuildConfig

interface NetworkConfigs {
    object Keys {
        const val ACCEPT = "accept"
        const val ACCEPT_ENCODING = "accept-encoding"
        const val ACCEPT_LANGUAGE = "accept-language"
        const val USER_AGENT = "User-Agent"
        const val X_ENVIRONMENT_FLAG = "x-environment-flag"
        const val X_CHANNEL = "x-channel"
        const val X_TRACE_ID = "x-trace-id"
        const val CONTENT_ENCODING = "Content-Encoding"
        const val CONTENT_TYPE = "content-type"
        const val PLATFORM = "platform"
        const val API_KEY = "api_key"
    }

    object Values {
        const val TIME_OUT = 60L
        const val ACCEPT_VALUE = "application/json"
        const val ACCEPT_ENCODING_GZIP = "gzip, deflate, br"
        const val CONTENT_ENCODING_GZIP = "gzip"
        const val ANDROID = "Android"
    }

    val isLogging: Boolean get() = BuildConfig.LOGGING

    val baseUrl: String
    val apiKey: String
}

class DefaultNetworkConfigs : NetworkConfigs {
   /* override val baseUrl: String get() = "https://api.themoviedb.org/3/"
    override val apiKey: String get() = "e54d1df6bcc26b67ccadf98cea21c3f3"*/
   override val baseUrl: String get() = BuildConfig.BASE_URL
    override val apiKey: String get() = BuildConfig.API_KEY
}