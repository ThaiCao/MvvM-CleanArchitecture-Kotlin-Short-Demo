package com.example.mydemo.remote.di

import android.content.Context
import com.example.mydemo.remote.network.*
import com.example.mydemo.remote.network.NetworkConfigs.Keys.API_KEY
import com.example.mydemo.remote.network.NetworkConfigs.Keys.CONTENT_TYPE
import com.example.mydemo.remote.network.NetworkConfigs.Keys.PLATFORM
import com.example.mydemo.remote.network.NetworkConfigs.Values.ACCEPT_VALUE
import com.example.mydemo.remote.network.NetworkConfigs.Values.ANDROID
import com.example.mydemo.remote.network.NetworkConfigs.Values.TIME_OUT
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single {
        createRetrofit(
            okHttpClient = get(),
            convertFactory = get(),
            flowFactory = get(),
            networkConfigs = get(),
        )
    }

    single {
        provideOkHttpClient(
            cache = provideCache(context = androidApplication()),
            interceptors = provideInterceptors(
                networkConfigs = get()
            )
        )
    }

    single<NetworkConfigs> { DefaultNetworkConfigs() }

    single<Converter.Factory> {
        GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
    }

    single<CallAdapter.Factory> {
        FlowCallAdapterFactory(
            apiResponseHandler = get(),
            networkConnection = get(),
            apiErrorParser = get()
        )
    }

    single<ApiResponseHandler> {
        DefaultApiResponseHandler(networkConnection = get(), apiErrorParser = get())
    }

    single<NetworkConnection> { NetworkConnectionImpl(context = androidApplication()) }

    single<ApiErrorParser> { ApiErrorParserImpl() }
}

private fun createRetrofit(
    okHttpClient: OkHttpClient,
    convertFactory: Converter.Factory,
    flowFactory: CallAdapter.Factory,
    networkConfigs: NetworkConfigs
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(networkConfigs.baseUrl)
        .addConverterFactory(convertFactory)
        .addCallAdapterFactory(flowFactory)

        .client(okHttpClient)
        .build()
}

private fun provideOkHttpClient(
    interceptors: List<Interceptor>,
    cache: Cache
): OkHttpClient {
    return OkHttpClient.Builder().apply {

        interceptors.forEach {
            addInterceptor(it)
        }

        connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        readTimeout(TIME_OUT, TimeUnit.SECONDS)

        cache(cache)
    }.build()
}

private fun provideInterceptors(
    networkConfigs: NetworkConfigs
): List<Interceptor> {
    return listOf(
        provideHeaderInterceptor(networkConfigs),
        provideGzipInterceptor(),
        provideLoggingInterceptor(networkConfigs)
    )
}

fun provideGzipInterceptor(): Interceptor {
    return GzipInterceptor()
}

private fun provideLoggingInterceptor(networkConfigs: NetworkConfigs): Interceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (networkConfigs.isLogging) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE
    return logging
}

private fun provideHeaderInterceptor(
    networkConfigs: NetworkConfigs
): Interceptor {
    return Interceptor { chain ->
        val originalRequest = chain.request()
        val newHttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter(API_KEY, networkConfigs.apiKey).build()
        val requestBuilder = originalRequest.newBuilder().url(newHttpUrl)
            .addHeader(CONTENT_TYPE, ACCEPT_VALUE)
            .addHeader(PLATFORM, ANDROID)
        chain.proceed(requestBuilder.build())
    }
}

private fun provideCache(context: Context): Cache {
    val size = (10 * 1024 * 1024).toLong() // 10 Mb
    return Cache(context.cacheDir, size)
}

