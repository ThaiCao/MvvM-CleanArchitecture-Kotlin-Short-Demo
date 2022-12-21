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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.common))
    implementation(project(Modules.deeplink))
    implementation(project(Modules.language))
    implementation(project(Modules.presentation))
    implementation(AndroidX.recyclerview)
    implementation(AndroidX.fragment_ktx)
    implementation(Kotlin.core)
    implementation(AndroidX.navigationFragment)
    implementation(AndroidX.navigationUi)
    implementation(Image.glide)
    kapt(Image.glideCompiler)
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("com.google.android.material:material:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
