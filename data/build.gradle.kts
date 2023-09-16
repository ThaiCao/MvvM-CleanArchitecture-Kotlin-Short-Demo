plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.detektPlugin)
    id(Plugins.kotlin_parcelize)
    id(Plugins.kotlinKapt)
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
    implementation(project(Modules.common))
    implementation(project(Modules.model))
//    implementation(project(Modules.mapper))
    implementation(project(Modules.domain))

    implementation(Kotlin.core)
    implementation(Kotlin.kotlin)

    implementation(Di.core)

    implementation(Network.gson)

    implementation(Coroutines.coroutines)
    implementation(Database.room)
    kapt(Database.roomKapt)

    implementation(Timber.timber)
    implementation(Timber.timberkt)

    testImplementation(Test.junit)
    testImplementation(Test.coroutine)
    testImplementation(Test.mockk)
    testImplementation(Test.test_core)
    testImplementation(project(Modules.testing, "testArtifacts"))
}
