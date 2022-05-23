// for plugins
object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlin_parcelize = "kotlin-parcelize"
    const val kotlinKapt = "kotlin-kapt"
    const val koin = "koin"
    const val jacoco = "com.vanniktech.android.junit.jacoco"
    const val kotlin = "kotlin"
    const val java = "java-library"
}

//for classpath
object ClassPaths {
    const val androidToolsGradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val koinGradlePlugin = "org.koin:koin-gradle-plugin:${Versions.koin}"
    const val jacocoGradlePlugin = "com.vanniktech:gradle-android-junit-jacoco-plugin:${Versions.jacoco}"
}

object Kotlin {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
}

object AndroidX {
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragment}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationUi}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomKapt = "androidx.room:room-compiler:${Versions.room}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val securityCrypto = "androidx.security:security-crypto:${Versions.securityCrypto}"
}

object Google {
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object Image {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Database {
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomKapt = "androidx.room:room-compiler:${Versions.room}"
}

object Di {
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompat = "io.insert-koin:koin-android-compat:${Versions.koin}"
    const val koinWorkmanager = "io.insert-koin:koin-androidx-workmanager:${Versions.koin}"
    const val koinNavigation = "io.insert-koin:koin-androidx-navigation:${Versions.koin}"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Network {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingInterceptor}"
}

object Test {
    const val junit = "junit:junit:${Versions.junit}"
    const val core = "android.arch.core:core-testing:${Versions.coreTesting}"
    const val test_core = "androidx.test:core:${Versions.testCore}"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.corountinesTest}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val toolsTest = ":tooltest"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
}

object Modules {
    const val data = ":data"
    const val domain = ":domain"
    const val presentation = ":presentation"
    const val local = ":local"
    const val remote = ":remote"
    const val common = ":common"
}

object CodeQuality {
    const val pinterestKtlint = "com.pinterest:ktlint:${Versions.pinterestKtlint}"
}

