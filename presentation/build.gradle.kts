plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlin_parcelize)
}

android {

    compileSdk = 32
    defaultConfig {
        minSdk = 24
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.local))
    implementation(Kotlin.kotlin)
    implementation(Di.core)
    implementation(Di.koin)
    implementation(Coroutines.core)
    implementation(Coroutines.coroutines)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.liveData)

    testImplementation(Test.junit)
    testImplementation(Test.coroutine)
    testImplementation(Test.mockk)
    testImplementation(Test.test_core)
    testImplementation(project(Test.toolsTest, "testArtifacts"))
}