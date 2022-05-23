
plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    jacoco
}
dependencies {
    implementation(project(Modules.common))
    implementation(project(Modules.data))

    implementation(Kotlin.kotlin)
    implementation(Di.core)
    implementation(Di.koin)
    implementation(Coroutines.core)
    implementation(Coroutines.coroutines)
    implementation(Database.room)
    kapt(Database.roomKapt)
    implementation(Google.gson)

    testImplementation(Test.junit)
    testImplementation(Test.coroutine)
    testImplementation(Test.mockk)
    testImplementation(Test.test_core)
    testImplementation(project(Test.toolsTest, "testArtifacts"))
}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    compileSdk = 32
}
