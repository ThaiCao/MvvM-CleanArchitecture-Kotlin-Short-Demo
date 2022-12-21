plugins {
//    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
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

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("com.google.android.material:material:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
