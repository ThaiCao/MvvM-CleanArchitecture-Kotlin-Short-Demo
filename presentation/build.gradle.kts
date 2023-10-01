plugins {
//    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
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
    implementation(project(Modules.domain))
    implementation(project(Modules.common))

    implementation(Kotlin.core)
    implementation(Kotlin.kotlin)

    implementation(Di.core)
    implementation(Di.koin)

    implementation(AndroidX.viewModel)
    implementation(AndroidX.liveData)

    implementation(Timber.timber)
    implementation(Timber.timberkt)

    implementation(Image.glide)
    kapt(Image.glideCompiler)
    testImplementation("junit:junit:4.13.2")
}
