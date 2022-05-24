plugins {
//    id(Plugins.kotlin)
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    jacoco
}


dependencies {
    implementation(Kotlin.kotlin)

    implementation(Di.core)

    implementation(Coroutines.core)
    implementation(Coroutines.coroutines)

    implementation(AndroidX.viewModel)
    implementation(AndroidX.liveData)

    testImplementation(Test.junit)
    testImplementation(Test.coroutine)
    testImplementation(Test.mockk)
    testImplementation(project(Test.toolsTest, "testArtifacts"))
}

android {
    compileSdk = 32
}
