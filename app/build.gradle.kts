import com.android.build.api.dsl.BaseFlavor
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.SigningConfig
import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.detektPlugin)
    id(Plugins.kotlin_parcelize)
    id(Plugins.kotlinKapt)
//    id(Plugins.google_service)
//    id(Plugins.crashlytics)
//    id(Plugins.firebase_pref)
    jacoco
}

apply {
    from(rootProject.file("ktlint.gradle.kts"))
    from(rootProject.file("detekt.gradle"))
}

// For flavors usage
fun BaseFlavor.buildConfigBoolean(name: String, value: Boolean) =
    buildConfigField("Boolean", name, value.toString())

fun BaseFlavor.buildConfigString(name: String, value: String) =
    buildConfigField("String", name, value)

// For build types usage
fun BuildType.buildConfigString(name: String, value: String) =
    buildConfigField("String", name, value)

fun SigningConfig.setSigningConfig(signingProperties: Properties) {
    this.apply {
        storeFile = rootProject.file(signingProperties.getProperty("storeFile"))
        storePassword = signingProperties.getProperty("storePassword")
        keyAlias = signingProperties.getProperty("keyAlias")
        keyPassword = signingProperties.getProperty("keyPassword")
    }
}

fun com.android.build.gradle.internal.dsl.BaseAppModuleExtension.setFlavorConfigByBuildType(
    productFlavor: com.android.build.api.dsl.ProductFlavor
) {
    buildTypes.configureEach {
        if (this.isDebuggable) {
            productFlavor.apply {
                buildConfigBoolean("LOGGING", true)
            }
            manifestPlaceholders["firebase_performance_logcat_enabled"] = true
        } else {
            productFlavor.apply {
                buildConfigBoolean("LOGGING", true)
            }
            manifestPlaceholders["firebase_performance_logcat_enabled"] = false
        }
    }
}

android {
    compileSdk = (ConfigData.compileSdkVersion)
    buildToolsVersion = (ConfigData.buildToolsVersion)

    defaultConfig {
        applicationId = ConfigData.applicationId
        minSdk = (ConfigData.minSdkVersion)
        targetSdk = (ConfigData.targetSdkVersion)
        versionCode = getVersionCode()

        multiDexEnabled = true

        applicationVariants.all {
            val flavor = flavorName
            val versionName = versionName
            outputs.map { it as? ApkVariantOutputImpl }.forEach {
                it?.outputFileName = "app_${flavor}_${versionName}_${versionCode}.apk"
                println(it?.outputFile?.name)
            }
        }

        buildConfigString("ENVIRONMENT_FLAG", "\"development\"")
        buildConfigBoolean("LOGGING", true)
        buildConfigString("APP_NAME", "\"Demo Structure\"")
        buildConfigBoolean("IS_PRODUCTION", false)
        buildConfigString("IMAGE_BASE_URL", properties["IMAGE_BASE_URL"].toString() )

        resValue("string", "map_api_keys", properties["GOOGLE_MAP_KEY"].toString())
        resValue("string", "dl_default", properties["DL_DEFAULT"].toString())

        manifestPlaceholders["firebase_performance_logcat_enabled"] = true

    }

    signingConfigs {
        create(SigningConfigs.development) {
            val signingDevProperties = Properties()
            signingDevProperties.load(FileInputStream(rootProject.file("signing-dev.properties")))
            setSigningConfig(signingProperties = signingDevProperties)
        }

        create(SigningConfigs.production) {
            val signingProProperties = Properties()
            signingProProperties.load(FileInputStream(rootProject.file("signing-pro.properties")))
            setSigningConfig(signingProperties = signingProProperties)
        }
    }

    buildTypes {
        getByName(BuildTypes.debug) {
            isMinifyEnabled = false
            isDebuggable = true

            signingConfig = signingConfigs.getByName(SigningConfigs.development)

            isTestCoverageEnabled = true
        }

        getByName(BuildTypes.release) {
            isMinifyEnabled = true
            isShrinkResources = true

            isDebuggable = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",

                "../domain/domain-consumer-rules.pro",

                "../proguard-rules/retrofit.pro",
                "../proguard-rules/okhttp.pro",
                "../proguard-rules/okio.pro",
                "../proguard-rules/glide.pro"
            )
        }
    }

    flavorDimensions.add(ConfigData.dimension)
    productFlavors {

        create(Environment.DEV) {
            dimension = (ConfigData.dimension)
            applicationIdSuffix = ".dev"

            versionName = "${getVersionName()}-dev"

            signingConfig = signingConfigs.getByName(SigningConfigs.development)

            buildConfigString("BASE_CONFIG_URL", properties["BASE_CONFIG_URL_DEV"].toString())
            buildConfigString("API_KEY", properties["API_KEY_DEV"].toString())

            setFlavorConfigByBuildType(this)
        }

        create(Environment.PRO) {
            dimension = (ConfigData.dimension)
            versionName = getVersionName()
            signingConfig = signingConfigs.getByName(SigningConfigs.production)

            buildConfigString("ENVIRONMENT_FLAG", "\"production\"")
            buildConfigBoolean("LOGGING", false)

            buildConfigBoolean("IS_PRODUCTION", true)

            buildConfigString("BASE_CONFIG_URL", properties["BASE_CONFIG_URL_PRO"].toString())
            buildConfigString("API_KEY", properties["API_KEY_PRO"].toString())

            setFlavorConfigByBuildType(this)
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xallow-result-return-type"
    }

    //ignore duplicate package
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }

    bundle {
        language {
            enableSplit = false
        }
        density {
            enableSplit = true
        }
        abi {
            enableSplit = true
        }
    }

    buildFeatures {
        viewBinding = true
    }

//    lint {
//        isCheckReleaseBuilds = false
//        isAbortOnError = false
//    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
}

tasks {
    withType<io.gitlab.arturbosch.detekt.Detekt> {
        // Target version of the generated JVM bytecode. It is used for type resolution.
        this.jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(Modules.model))
    implementation(project(Modules.mapper))
    implementation(project(Modules.data))
    implementation(project(Modules.domain))
    implementation(project(Modules.presentation))
    implementation(project(Modules.common))
    implementation(project(Modules.uibase))
    implementation(project(Modules.language))

    implementation(Di.core)
    implementation(Di.koin)

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
    implementation(AndroidX.java8)
    implementation(AndroidX.liveData)
    implementation(AndroidX.fragment_ktx)
    implementation(AndroidX.refreshLayout)
    implementation(AndroidX.security_crypto)

    implementation(AndroidX.viewPager2)

    implementation(Network.retrofit)
    implementation(Network.gson)
    implementation(Network.okhttp_logging_interceptor)

    implementation(Timber.timber)
    implementation(Timber.timberkt)

    implementation(Shimmer.shimmer)

    implementation(Google.gson)

    implementation(AndroidX.browser)
    implementation(AndroidX.splash)

//    implementation("androidx.databinding:viewbinding:7.1.2")

    implementation(Image.glide)
    implementation(Image.glideOkhttp)
    kapt(Image.glideCompiler)

    testImplementation(Test.junit)
    testImplementation(Test.coroutine)
    testImplementation(Test.core)
    testImplementation(Test.test_core)
    testImplementation(Test.mockk)
    testImplementation(Test.robolectric)
//    testImplementation(project(Modules.tools_test, "testArtifacts"))
}


fun getVersionCode(): Int {
    val properties = Properties().apply {
        load(FileInputStream(rootProject.file("app_version.properties")))
    }

    return properties.getProperty("VERSION_CODE").trim().toInt()
}

fun getVersionName(): String {
    val properties = Properties().apply {
        load(FileInputStream(rootProject.file("app_version.properties")))
    }

    val majorVersion = properties.getProperty("MAJOR_VERSION").trim().toInt()
    val minorVersion = properties.getProperty("MINOR_VERSION").trim().toInt()
    val patchVersion = properties.getProperty("PATCH_VERSION").trim().toInt()

    return "$majorVersion.$minorVersion.$patchVersion"
}

fun increaseMajorVersion() {
    val properties = Properties().apply {
        load(FileInputStream(rootProject.file("app_version.properties")))
    }
    val majorVersion = properties.getProperty("MAJOR_VERSION").trim().toInt() + 1
    properties.setProperty("MAJOR_VERSION", majorVersion.toString())

    properties.setProperty("MINOR_VERSION", "0")
    properties.setProperty("PATCH_VERSION", "0")

    properties.increaseVersionCode()

    properties.store(FileOutputStream(rootProject.file("app_version.properties")), "")
}

fun increaseMinorVersion() {
    val properties = Properties().apply {
        load(FileInputStream(rootProject.file("app_version.properties")))
    }

    val minorVersion = properties.getProperty("MINOR_VERSION").trim().toInt() + 1
    properties.setProperty("MINOR_VERSION", minorVersion.toString())

    properties.setProperty("PATCH_VERSION", "0")

    properties.increaseVersionCode()

    properties.store(FileOutputStream(rootProject.file("app_version.properties")), "")
}

fun increasePatchVersion() {
    val properties = Properties()
    properties.load(FileInputStream(rootProject.file("app_version.properties")))

    val patchVersion = properties.getProperty("PATCH_VERSION").trim().toInt() + 1
    properties.setProperty("PATCH_VERSION", patchVersion.toString())

    properties.increaseVersionCode()

    properties.store(FileOutputStream(rootProject.file("app_version.properties")), "")
}

fun Properties.increaseVersionCode() {
    val versionCode = this.getProperty("VERSION_CODE").trim().toInt() + 1
    this.setProperty(
        "VERSION_CODE",
        versionCode.toString()
    )
}

fun logVersionChanged(function: () -> Unit) {
    val current = "${getVersionName()}(${getVersionCode()})"
    function.invoke()
    val newVersion = "${getVersionName()}(${getVersionCode()})"
    println("Version changed: $current -> $newVersion")
}

fun createReleaseBranch() {
    println(">>>>>>>>>>> createReleaseBranch()")
    val versionCode: Int = getVersionCode()
    val versionName = getVersionName()
    val releaseName = "release_${versionName}($versionCode)"
    val description = "Release version: $versionName build: $versionCode"

    val branchName = "release/$releaseName"
    runGitCommand("git", "checkout", "-b", branchName)
    runGitCommand("git", "commit", "-am", description)
}

fun addGitTagForRelease() {
    println(">>>>>>>>>>>addGitTagForRelease()")
    val versionCode: Int = getVersionCode()
    val versionName = getVersionName()
    val releaseName = "release_${versionName}($versionCode)"
    val branchName = "release/$releaseName"
    val description = "Release version: $versionName build: $versionCode"

    runGitCommand("git", "checkout", "master")
    runGitCommand("git", "fetch")
    runGitCommand("git", "merge", branchName)
    runGitCommand("git", "commit", "-am", description)
    runGitCommand("git", "tag", "-a", "-m", description, releaseName)

    runGitCommand("git", "checkout", "develop")
    runGitCommand("git", "fetch")
    runGitCommand("git", "merge", branchName)
    runGitCommand("git", "commit", "-am", description)

    runGitCommand("git", "branch", "-d", branchName)
}

fun createGitHotFix() {
    println(">>>>>>>>>>>createGitHotFix()")
    val versionCode = getVersionCode()
    val versionName = getVersionName()
    val tagName = "release_${versionName}($versionCode)"

    runGitCommand("git", "fetch")
    runGitCommand("git", "checkout", "-b", "hotfix/$tagName", tagName)
}

fun haveDoneHotFix() {
    println(">>>>>>>>>>>haveDoneHotFix()")
    val versionCode: Int = getVersionCode()
    val versionName = getVersionName()
    val hotfixName = "hotfix_${versionName}($versionCode)"
    val branchName = "hotfix/$hotfixName"
    val description = "Hot fix version: $versionName build: $versionCode"

    runGitCommand("git", "checkout", "master")
    runGitCommand("git", "fetch")
    runGitCommand("git", "merge", branchName)
    runGitCommand("git", "commit", "-am", description)
    runGitCommand("git", "tag", "-a", "-m", description, hotfixName)

    runGitCommand("git", "checkout", "develop")
    runGitCommand("git", "fetch")
    runGitCommand("git", "merge", branchName)
    runGitCommand("git", "commit", "-am", description)

    runGitCommand("git", "branch", "-d", branchName)
}

fun runGitCommand(vararg command: String) {
    val commands = command.toList()
    val process = ProcessBuilder(commands).start()
    process.waitFor()
    process.errorStream?.getString()?.print("Command: ${commands.joinToString(" ")} fail")
    process.inputStream.getString()?.print("Command: ${commands.joinToString(" ")} info")
    process.destroy()
}

fun String.print(tag: String) {
    println("$tag: $this")
}

fun java.io.InputStream?.getString(): String? {
    this ?: return null
    return bufferedReader().use { it.readText() }
}

tasks.register("haveDoneHotFix") {
    doLast {
        haveDoneHotFix()
    }
}

tasks.register("createGitHotFix") {
    doLast {
        createGitHotFix()
    }
}

tasks.register("addGitTagForRelease") {
    doLast {
        addGitTagForRelease()
    }
}

tasks.register("createReleaseBranch") {
    doLast {
        createReleaseBranch()
    }
}

tasks.register("increaseMinorVersion") {
    doLast {
        logVersionChanged {
            increaseMinorVersion()
        }
    }
}

tasks.register("increaseMajorVersion") {
    doLast {
        logVersionChanged {
            increaseMajorVersion()
        }
    }
}

tasks.register("increasePatchVersion") {
    doLast {
        logVersionChanged {
            increasePatchVersion()
        }
    }
}
