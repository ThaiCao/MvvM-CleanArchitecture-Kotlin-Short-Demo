package com.example.structure.network

import com.example.structure.BuildConfig

interface NetworkConfigs {
    object Keys {
        const val ACCEPT = "accept"
        const val ACCEPT_ENCODING = "accept-encoding"
        const val ACCEPT_LANGUAGE = "accept-language"
        const val CONTENT_ENCODING = "Content-Encoding"
        const val CONTENT_TYPE = "content-type"
        const val PLATFORM = "platform"
        const val UUID = "uuid"
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
    val acceptLanguage: String
    val environmentFlag: String
}

class DefaultNetworkConfigs : NetworkConfigs {
    override val baseUrl: String
        get() = BuildConfig.BASE_CONFIG_URL
    override val acceptLanguage: String
        get() = "en-US,en;q=0.9,vi;q=0.8"
    override val environmentFlag: String
        get() = BuildConfig.ENVIRONMENT_FLAG

}
