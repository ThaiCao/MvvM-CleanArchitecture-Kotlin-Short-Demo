
plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlin_parcelize)
    jacoco
}

android {
    compileSdk =  32

    defaultConfig {
        applicationId = "com.example.mydemo"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

//    signingConfigs {
//        getByName("debug") {
//            keyAlias = "debug"
//            keyPassword = "my debug key password"
//            storeFile = file("/home/miles/keystore.jks")
//            storePassword = "my keystore password"
//        }
//        create("release") {
//            keyAlias = "release"
//            keyPassword = "my release key password"
//            storeFile = file("/home/miles/keystore.jks")
//            storePassword = "my keystore password"
//        }
//    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources =  false
//            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources =  true
//            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding =  true
    }
    lintOptions {
        isCheckReleaseBuilds  = false
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(project(Modules.common))
    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.presentation))
    implementation(project(Modules.local))
    implementation(project(Modules.remote))

    implementation(Kotlin.kotlin)
    implementation(Di.core)
    implementation(Di.koin)
    implementation(Di.koinCompat)
    implementation(Di.koinWorkmanager)

    implementation(Coroutines.coroutines)

    implementation(AndroidX.recyclerview)
    implementation(AndroidX.multidex)
    implementation(AndroidX.design)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.constraint)
    implementation(AndroidX.navigationFragment)
    implementation(AndroidX.navigationUi)
    implementation(AndroidX.room)
    implementation(AndroidX.viewModel)
    implementation(AndroidX.liveData)
    implementation(AndroidX.fragmentKtx)
    implementation(AndroidX.securityCrypto)

    implementation(Network.retrofit)
    implementation(Network.gson)

    implementation(Image.glide)
    kapt(Image.glideCompiler)

    testImplementation(Test.junit)
    testImplementation(Test.coroutine)
    testImplementation(Test.core)
    testImplementation(Test.test_core)
    testImplementation(Test.mockk)
    testImplementation(Test.robolectric)
    testImplementation(project(Test.toolsTest, "testArtifacts"))
}