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

    const val viewPager2 = "androidx.viewpager2:viewpager2:1.0.0"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val java8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"

    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"
    const val refreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.refresh_layout}"

    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val security_crypto = "androidx.security:security-crypto:${Versions.security_crypto}"
    const val browser = "androidx.browser:browser:${Versions.browser}"
}

object Di {
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Network {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp_logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"
}

object Timber {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val timberkt = "com.github.ajalt:timberkt:${Versions.timberkt}"
}

object Shimmer {
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
}

object Image {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Database {
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomKapt = "androidx.room:room-compiler:${Versions.room}"
}

object CodeQuality {
    const val detekt_formatting =
        "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt_version}"
    const val detekt_cli = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detekt_version}"

    const val pinterest_ktlint = "com.pinterest:ktlint:${Versions.pinterest_ktlint}"
}

object Google {
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object Test {
    const val junit = "junit:junit:${Versions.junit}"
    const val core = "android.arch.core:core-testing:${Versions.core_testing}"
    const val test_core = "androidx.test:core:${Versions.test_core}"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.corountines_test}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
}