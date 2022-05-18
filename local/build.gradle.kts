
plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
}
dependencies {
    implementation(project(Modules.data))

    implementation(Kotlin.kotlin)
    implementation(Di.core)
    implementation(Coroutines.core)
    implementation(Coroutines.coroutines)
    implementation(Database.room)
    kapt(Database.roomKapt)
    implementation(Google.gson)
}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    compileSdk = 31
}
