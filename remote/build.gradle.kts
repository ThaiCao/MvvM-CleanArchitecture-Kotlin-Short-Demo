plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
}

android {
    buildTypes {
        debug {
            buildConfigField("String","API_KEY",properties["API_KEY"].toString())
            buildConfigField("String","BASE_URL",properties["BASE_URL"].toString())
            buildConfigField("Boolean","LOGGING", "true")
        }
        release {
            buildConfigField("String","API_KEY",properties["API_KEY"].toString())
            buildConfigField("String","BASE_URL",properties["BASE_URL"].toString())
            buildConfigField("Boolean","LOGGING", "true")
        }
    }
}

dependencies {
    implementation(project(Modules.data))

    implementation(Kotlin.kotlin)
    implementation(Di.core)
    implementation(Di.koin)
    implementation(Coroutines.core)
    implementation(Coroutines.coroutines)
    implementation(Database.room)
    kapt(Database.roomKapt)
    implementation(Network.retrofit)
    implementation(Network.gson)
    implementation(Network.okhttpLoggingInterceptor)
}

android {
    compileSdk = 32
}
