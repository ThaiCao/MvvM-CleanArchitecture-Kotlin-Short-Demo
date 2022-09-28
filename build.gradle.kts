apply {
    plugin(Plugins.detektPlugin)
    plugin(Plugins.jacoco)
}

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        classpath(ClassPaths.android_tools_gradle)
        classpath(ClassPaths.kotlin_gradle_plugin)
        classpath(ClassPaths.detekt_gradle_plugin)
        classpath(ClassPaths.jacoco_gradle_plugin)
//        classpath(ClassPaths.firebase_pref)
//        classpath(ClassPaths.google_service)
//        classpath(ClassPaths.firebase_crashlytics_gradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    }
}

//allprojects {
//    repositories {
//        google()
//        jcenter()
//        mavenCentral()
//        maven(url = "https://jitpack.io")
//    }
//
//    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
//        kotlinOptions {
//            freeCompilerArgs = freeCompilerArgs + arrayOf(
//                "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
//                "-Xuse-experimental=kotlinx.coroutines.InternalCoroutinesApi",
//                "-Xuse-experimental=org.koin.core.component.KoinApiExtension",
//                "-Xuse-experimental=org.koin.core.annotation.KoinInternal"
//            )
//        }
//    }
//
//    (rootProject.extensions["junitJacoco"] as? com.vanniktech.android.junit.jacoco.JunitJacocoExtension)
//        ?.apply {
//            jacocoVersion = "0.8.7"
//            excludes = listOf(
//
//                "**/data/di/*",
//                "**/data/*/di/*",
//                "**/data/*/dto/*",
//                "**/data/*/db/*",
//
//                "**/domain/*/di/*",
//                "**/domain/model/*",
//
//                "**/presentation/di/*",
//                "**/presentation/model/*",
//                "**/presentation/util/*",
//
//                "**/structure/common/base/*",
//                "**/structure/common/util/*ExtKt.*",
//
//                "**/structure/BuildConfig.*"
//            )
////            includeNoLocationClasses = true
//        }
//}

val mergeTestReport = tasks.register<TestReport>("mergeTestReports") {
    destinationDir = file("$buildDir/reports/tests/test")
    reportOn(subprojects.mapNotNull {
        it.tasks.findByPath("test")
    })
    reportOn(subprojects.mapNotNull {
        it.tasks.findByPath("testDebugUnitTest")
    })
}
