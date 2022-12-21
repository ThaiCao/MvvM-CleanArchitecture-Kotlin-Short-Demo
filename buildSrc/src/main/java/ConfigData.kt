object ConfigData {
    const val applicationId = "com.example.structure"
    const val compileSdkVersion = 32
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 24
    const val targetSdkVersion = 32
    const val versionCode = 1
    const val versionName = "1.0"
    const val dimension = "environment"
}

fun getVersionCode(): Int {
    return ConfigData.versionCode
}

fun getVersionName(): String {
    return ConfigData.versionName
}
