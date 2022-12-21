package com.example.structure.di

import android.content.Context
import com.example.structure.common.extend.isNotNullOrBlank
import com.example.structure.data.di.NameInjection.NAME_RETROFIT_FOR_CONFIG_SERVICE
import com.example.structure.data.di.NameInjection.NAME_RETROFIT_FOR_CUSTOMER_SERVICE
import com.example.structure.network.*
import com.example.structure.network.HeaderProvider.Companion.PREF_HEADER_PROVIDER
import com.example.structure.network.NetworkConfigs.Keys.ACCEPT
import com.example.structure.network.NetworkConfigs.Keys.ACCEPT_LANGUAGE
import com.example.structure.network.NetworkConfigs.Keys.CONTENT_TYPE
import com.example.structure.network.NetworkConfigs.Keys.PLATFORM
import com.example.structure.network.NetworkConfigs.Keys.UUID
import com.example.structure.network.NetworkConfigs.Values.ACCEPT_VALUE
import com.example.structure.network.NetworkConfigs.Values.ANDROID
import com.example.structure.network.NetworkConfigs.Values.TIME_OUT
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {


    single {
        createRetrofit(
            okHttpClient = get(),
            convertFactory = get(),
            flowFactory = get(),
            networkConfigs = get(),
        )
    }

//    single(named(NAME_RETROFIT_FOR_CONFIG_SERVICE)) {
//        createConfigRetrofit(
//            okHttpClient = get(),
//            convertFactory = get(),
//            flowFactory = get(),
//            networkConfigs = get(),
//        )
//    }

    single {
        provideOkHttpClient(
            cache = provideCache(context = androidApplication()),
            interceptors = provideInterceptors(
                networkConfigs = get(),
                headerProvider = get()
            )
        )
    }

    single<NetworkConfigs> { DefaultNetworkConfigs() }

    single(named(PREF_HEADER_PROVIDER)) {
        provideSharePreferences(
            context = androidContext(),
            fileName = PREF_HEADER_PROVIDER
        )
    }

    single<HeaderProvider> {
        HeaderProviderImpl(
            pref = get(named(PREF_HEADER_PROVIDER))
        )
    }

    factory<ErrorMapper> { ErrorMapperImpl() }
    single<ApiResponseHandler> {
        DefaultApiResponseHandler(networkConnection = get(), apiErrorParser = get())
    }
    single<ApiErrorParser> { ApiErrorParserImpl(errorMapper = get()) }
    single<NetworkConnection> { NetworkConnectionImpl(context = androidApplication()) }

//    single<ApiErrorParser> { ApiErrorParserImpl() }

//    single<CallAdapter.Factory>(named(NAME_RETROFIT_FOR_CUSTOMER_SERVICE)) {

    single<CallAdapter.Factory> {
        FlowCallAdapterFactory(
            apiResponseHandler = get(),
            networkConnection = get(),
            apiErrorParser = get()
        )
    }

    single<Converter.Factory> {
        GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
    }
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

private fun createConfigRetrofit(
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
    networkConfigs: NetworkConfigs,
    headerProvider: HeaderProvider
): List<Interceptor> {
    return listOf(
        provideHeaderInterceptor(networkConfigs, headerProvider),
//        provideGzipInterceptor(),
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
    networkConfigs: NetworkConfigs,
    headerProvider: HeaderProvider
): Interceptor {
    return Interceptor { chain ->
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .addHeader(CONTENT_TYPE, ACCEPT_VALUE)
            .addHeader(ACCEPT, ACCEPT_VALUE)
            .addHeader(PLATFORM, ANDROID)
            .addHeader(UUID, headerProvider.getUUID())

        if (networkConfigs.acceptLanguage.isNotNullOrBlank()) {
            requestBuilder.addHeader(ACCEPT_LANGUAGE, networkConfigs.acceptLanguage)
        }

//        chain.proceed(requestBuilder.build())
        chain.proceed(originalRequest.newBuilder().build())
    }
}

private fun provideCache(context: Context): Cache {
    val size = (10 * 1024 * 1024).toLong() // 10 Mb
    return Cache(context.cacheDir, size)
}
