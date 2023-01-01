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
    implementation(project(Modules.model))
    implementation(Kotlin.kotlin)
    implementation(Di.core)
    implementation(Coroutines.core)
    implementation(Coroutines.coroutines)
    testImplementation("junit:junit:4.13.2")
}


//val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
//compileKotlin.kotlinOptions {
//    freeCompilerArgs = freeCompilerArgs + "-Xallow-result-return-type"
//}
//
//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions.jvmTarget = "1.8"
//}
