plugins {
    id(Plugins.kotlin)
    jacoco
}


dependencies {
    implementation(Kotlin.kotlin)

    implementation(Di.core)

    implementation(Coroutines.core)
    implementation(Coroutines.coroutines)

    testImplementation(Test.junit)
    testImplementation(Test.coroutine)
    testImplementation(Test.mockk)
    testImplementation(project(Test.toolsTest, "testArtifacts"))
}